package com.hieplp.recipe.auth.command.service.impl;

import com.hieplp.recipe.auth.command.commands.register.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.command.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.response.auth.GenerateRegisterOtpResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.VerifyRegisterOtpResponse;
import com.hieplp.recipe.auth.command.service.AuthCommandService;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.query.queries.GetTodayOtpQuotaQuery;
import com.hieplp.recipe.common.enums.otp.OtpType;
import com.hieplp.recipe.common.exception.auth.ExceededOtpQuotaException;
import com.hieplp.recipe.common.exception.user.DuplicatedUsernameException;
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
        var doesUsernameExist = queryGateway.query(new CheckUsernameExistenceQuery(request.getUsername()), Boolean.class).join();
        if (doesUsernameExist) {
            log.error("Username {} is duplicated", request.getUsername());
            throw new DuplicatedUsernameException(String.format("Username %s is duplicated", request.getUsername()));
        }

        // Check if email exists
        var doesEmailExist = queryGateway.query(new CheckEmailExistenceQuery(request.getEmail()), Boolean.class).join();
        if (doesEmailExist) {
            log.error("Email {} is duplicated", request.getEmail());
            throw new DuplicatedUsernameException(String.format("Email %s is duplicated", request.getEmail()));
        }

        // Check if quota is exceeded
        var quota = queryGateway.query(new GetTodayOtpQuotaQuery(request.getEmail(), OtpType.REGISTER.getType()), Integer.class).join();
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
        return null;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        return null;
    }
}
