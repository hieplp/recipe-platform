package com.hieplp.recipe.auth.command.service.impl;

import com.hieplp.recipe.auth.command.commands.register.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.command.commands.register.VerifyRegisterOtpCommand;
import com.hieplp.recipe.auth.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.command.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.response.auth.GenerateRegisterOtpResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.VerifyRegisterOtpResponse;
import com.hieplp.recipe.auth.command.service.AuthCommandService;
import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.query.queries.otp.GetOtpQuery;
import com.hieplp.recipe.auth.query.queries.otp.GetTodayOtpQuotaQuery;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
import com.hieplp.recipe.common.enums.otp.OtpType;
import com.hieplp.recipe.common.exception.BadRequestException;
import com.hieplp.recipe.common.exception.auth.ExceededOtpQuotaException;
import com.hieplp.recipe.common.exception.auth.ExpiredOtpException;
import com.hieplp.recipe.common.exception.user.DuplicatedUsernameException;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import com.hieplp.recipe.common.query.queries.user.CheckEmailExistenceQuery;
import com.hieplp.recipe.common.query.queries.user.CheckUsernameExistenceQuery;
import com.hieplp.recipe.common.util.GeneratorUtil;
import com.hieplp.recipe.common.util.MaskUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private static final int DEFAULT_OTP_LENGTH = 6;
    private static final int DEFAULT_OTP_ID_LENGTH = 10;
    private static final int DEFAULT_USER_ID_LENGTH = 10;

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final AuthConfig authConfig;

    @Override
    public GenerateRegisterOtpResponse generateRegisterOtp(GenerateRegisterOtpRequest request) {
        log.info("Generate OTP for register with request: {}", request);

        // Check if username exists
        var doesUsernameExist = queryGateway.query(new CheckUsernameExistenceQuery(request.getUsername()), boolean.class).join();
        if (doesUsernameExist) {
            log.error("Username {} is duplicated", request.getUsername());
            throw new DuplicatedUsernameException(String.format("Username %s is duplicated", request.getUsername()));
        }

        // Check if email exists
        var doesEmailExist = queryGateway.query(new CheckEmailExistenceQuery(request.getEmail()), boolean.class).join();
        if (doesEmailExist) {
            log.error("Email {} is duplicated", request.getEmail());
            throw new DuplicatedUsernameException(String.format("Email %s is duplicated", request.getEmail()));
        }

        // Check if quota is exceeded
        var quota = queryGateway.query(new GetTodayOtpQuotaQuery(request.getEmail(), OtpType.REGISTER.getType()), int.class).join();
        log.debug("Current quota: {} and config quota: {}", quota, authConfig.getRegisterOtp().getQuota());
        if (quota >= authConfig.getRegisterOtp().getQuota()) {
            log.error("Quota is exceeded");
            throw new ExceededOtpQuotaException("Quota is exceeded");
        }

        //
        final var otpId = GeneratorUtil.randomString(DEFAULT_OTP_ID_LENGTH);
        final var otpCode = GeneratorUtil.generateOTP(DEFAULT_OTP_LENGTH);
        final var userId = GeneratorUtil.randomString(DEFAULT_USER_ID_LENGTH);
        final var issuedAt = LocalDateTime.now();
        final var expiredAt = issuedAt.plusSeconds(authConfig.getRegisterOtp().getExpirationTime());

        //
        final var command = CreateRegisterOtpCommand.builder()
                .otpId(otpId)
                .otpCode(otpCode)
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(request.getPassword())
                .userId(userId)
                .issuedAt(issuedAt)
                .expiredAt(expiredAt)
                .build();
        commandGateway.sendAndWait(command);

        return GenerateRegisterOtpResponse.builder()
                .otpId(otpId)
                .maskedEmail(MaskUtil.maskEmail(request.getEmail()))
                .expiredIn(authConfig.getRegisterOtp().getExpirationTime())
                .build();
    }

    @Override
    public VerifyRegisterOtpResponse verifyRegisterOtp(VerifyRegisterOtpRequest request) {
        log.info("Verify OTP for register with request: {}", request);

        var otp = queryGateway.query(new GetOtpQuery(request.getOtpId()), OtpEntity.class).join();

        // Check if OTP exists
        if (otp == null) {
            log.error("OTP {} not found", request.getOtpId());
            throw new NotFoundException("OTP not found");
        }

        // Check if OTP is for register
        if (!OtpType.REGISTER.getType().equals(otp.getType())) {
            log.error("OTP {} is not for register", request.getOtpId());
            throw new BadRequestException("OTP is not for register");
        }

        // Check OTP issuedAt
        if (otp.getIssuedAt().isAfter(LocalDateTime.now())) {
            log.error("OTP {} is not issued yet", request.getOtpId());
            throw new BadRequestException("OTP is not issued yet");
        }

        // Check if OTP is expired
        if (otp.getExpiredAt().isBefore(LocalDateTime.now())) {
            log.error("OTP {} is expired", request.getOtpId());
            throw new ExpiredOtpException("OTP is expired");
        }

        // Check if OTP is issued
        if (!OtpStatus.ACTIVATED.getStatus().equals(otp.getStatus())) {
            log.error("OTP {} is issued", request.getOtpId());
            throw new BadRequestException("OTP is issued");
        }

        // Check if OTP issue times is exceeded
        var quota = queryGateway.query(new GetTodayOtpQuotaQuery(otp.getSendTo(), OtpType.REGISTER.getType()), int.class).join();
        log.debug("Current quota: {} and config quota: {}", quota, authConfig.getRegisterOtp().getQuota());
        if (quota >= authConfig.getRegisterOtp().getWrongQuota()) {
            log.error("Quota is exceeded");
            throw new ExceededOtpQuotaException("Quota is exceeded");
        }

        var isVerified = otp.getOtpCode().equals(request.getOtpCode());

        if (isVerified) {
            // Call command to verify OTP
            var verifyOtpCommand = VerifyRegisterOtpCommand.builder()
                    .otpId(request.getOtpId())
                    .otpCode(request.getOtpCode())
                    .build();
            var response = commandGateway.send(verifyOtpCommand);
            var t = response.exceptionally(throwable -> {
                log.error("Error while verifying OTP: {}", throwable.getMessage());
                return null;
            }).join();
        } else {
            // Call command to save OTP history
        }


        return VerifyRegisterOtpResponse.builder()
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        return null;
    }
}
