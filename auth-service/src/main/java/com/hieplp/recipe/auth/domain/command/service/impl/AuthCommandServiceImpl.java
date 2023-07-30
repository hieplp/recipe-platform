package com.hieplp.recipe.auth.domain.command.service.impl;

import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.domain.command.commands.otp.forgot.create.CreateForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.ResendRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.GenerateForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ConfirmRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ResendRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.response.auth.GenerateRegisterOtpResponse;
import com.hieplp.recipe.auth.domain.command.service.AuthCommandService;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetOtpQuery;
import com.hieplp.recipe.common.enums.IdLength;
import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.enums.response.SuccessCode;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import com.hieplp.recipe.common.util.DateUtil;
import com.hieplp.recipe.common.util.GeneratorUtil;
import com.hieplp.recipe.common.util.MaskUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private static final int DEFAULT_OTP_LENGTH = 6;

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;
    private final AuthConfig authConfig;

    @Override
    public CompletableFuture<CommonResponse> generateRegisterOtp(GenerateRegisterOtpRequest request) {
        log.info("Generate OTP for register with request: {}", request);
        //
        final var otpId = GeneratorUtil.generateId(IdLength.OTP_ID);
        final var otpCode = GeneratorUtil.generateOTP(DEFAULT_OTP_LENGTH);
        final var userId = GeneratorUtil.generateId(IdLength.USER_ID);
        final var issuedAt = DateUtil.now();
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
                            .expiredAt(expiredAt.toString())
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
    public CompletableFuture<CommonResponse> resendRegisterOtp(ResendRegisterOtpRequest request) {
        log.info("Resend OTP for registration with request: {}", request);
        var resendCommand = ResendRegisterOtpCommand.builder()
                .otpId(request.getOtpId())
                .build();
        return commandGateway.send(resendCommand)
                .thenApply(it -> {
                    var otp = queryGateway.query(new GetOtpQuery(request.getOtpId()), OtpEntity.class).join();
                    var response = GenerateRegisterOtpResponse.builder()
                            .otpId(otp.getOtpId())
                            .maskedEmail(MaskUtil.maskEmail(otp.getSendTo()))
                            .expiredAt(otp.getExpiredAt().toString())
                            .expiredIn(authConfig.getRegisterOtp().getExpirationTime())
                            .build();
                    return new CommonResponse(SuccessCode.SUCCESS, response);
                })
                .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @Override
    public CompletableFuture<CommonResponse> generateForgotOtp(GenerateForgotOtpRequest request) {
        log.info("Generate OTP for forgot password with request: {}", request);

        final var otpId = GeneratorUtil.generateId(IdLength.OTP_ID);
        final var otpCode = GeneratorUtil.generateOTP(DEFAULT_OTP_LENGTH);
        final var issuedAt = DateUtil.now();
        final var expiredAt = issuedAt.plusSeconds(authConfig.getForgotOtp().getExpirationTime());

        final var command = CreateForgotOtpCommand.builder()
                .otpId(otpId)
                .otpCode(otpCode)
                .sendTo(request.getEmail())
                .issuedAt(issuedAt)
                .expiredAt(expiredAt)
                .build();
        return commandGateway.send(command)
                .thenApply(it -> {
                    var response = GenerateRegisterOtpResponse.builder()
                            .otpId(otpId)
                            .maskedEmail(MaskUtil.maskEmail(request.getEmail()))
                            .expiredAt(expiredAt.toString())
                            .expiredIn(authConfig.getForgotOtp().getExpirationTime())
                            .build();
                    return new CommonResponse(SuccessCode.SUCCESS, response);
                })
                .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
