package com.hieplp.recipe.auth.domain.command.service.impl;

import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ConfirmRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.response.auth.GenerateRegisterOtpResponse;
import com.hieplp.recipe.auth.domain.command.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.domain.command.service.AuthCommandService;
import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.enums.response.SuccessCode;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import com.hieplp.recipe.common.util.GeneratorUtil;
import com.hieplp.recipe.common.util.MaskUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<CommonResponse> generateRegisterOtp(GenerateRegisterOtpRequest request) {
        log.info("Generate OTP for register with request: {}", request);
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
        return commandGateway.send(command)
                .thenApply(it -> {
                    var response = GenerateRegisterOtpResponse.builder()
                            .otpId(otpId)
                            .maskedEmail(MaskUtil.maskEmail(request.getEmail()))
                            .expiredIn(authConfig.getRegisterOtp().getExpirationTime())
                            .build();
                    return new CommonResponse(SuccessCode.SUCCESS, response);
                })
                .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @Override
    public CompletableFuture<CommonResponse> confirmRegisterOtp(ConfirmRegisterOtpRequest request) {
        log.info("Confirm OTP for registration with request: {}", request);
        var confirmCommand = ConfirmRegisterOtpCommand.builder()
                .otpId(request.getOtpId())
                .otpCode(request.getOtpCode())
                .build();
        return commandGateway.send(confirmCommand)
                .thenApply(it -> new CommonResponse(SuccessCode.SUCCESS))
                .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @Override
    public RegisterResponse register(ConfirmRegisterOtpRequest request) {
        return null;
    }
}
