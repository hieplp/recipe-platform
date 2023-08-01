package com.hieplp.recipe.auth.domain.command.service.impl;

import com.hieplp.recipe.auth.config.component.UserRSAEncryption;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.ConfirmForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CreateForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.ResendForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.ResendRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.ConfirmForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.GenerateForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.ResendForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.login.LoginRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ConfirmRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ResendRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.response.login.LoginResponse;
import com.hieplp.recipe.auth.domain.command.payload.response.otp.OtpResponse;
import com.hieplp.recipe.auth.domain.command.service.AuthCommandService;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetOtpQuery;
import com.hieplp.recipe.common.entity.auth.OtpEntity;
import com.hieplp.recipe.common.entity.user.PasswordEntity;
import com.hieplp.recipe.common.entity.user.UserEntity;
import com.hieplp.recipe.common.enums.IdLength;
import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.enums.response.SuccessCode;
import com.hieplp.recipe.common.enums.token.TokenType;
import com.hieplp.recipe.common.enums.user.UserStatus;
import com.hieplp.recipe.common.exception.auth.PasswordNotMatchException;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import com.hieplp.recipe.common.query.queries.password.GetPasswordByUserIdQuery;
import com.hieplp.recipe.common.query.queries.user.GetUserByUsernameQuery;
import com.hieplp.recipe.common.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private static final int DEFAULT_OTP_LENGTH = 6;

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;
    private final AuthConfig authConfig;
    private final UserRSAEncryption userRSAEncryption;

    @Override
    public CompletableFuture<CommonResponse> generateRegisterOtp(GenerateRegisterOtpRequest request) {
        var password = new byte[0];
        try {
            log.info("Generate OTP for register with request: {}", request);
            //
            final var otpId = GeneratorUtil.generateId(IdLength.OTP_ID);
            final var otpCode = GeneratorUtil.generateOTP(DEFAULT_OTP_LENGTH);
            final var userId = GeneratorUtil.generateId(IdLength.USER_ID);
            final var issuedAt = DateUtil.now();
            final var expiredAt = issuedAt.plusSeconds(authConfig.getRegisterOtp().getExpirationTime());

            // Get password and salt
            var salt = GeneratorUtil.generateSalt();
            password = EncryptUtil.generatePassword(request.getPassword(), userRSAEncryption.getPrivateKey(), salt);

            //
            final var command = CreateRegisterOtpCommand.builder()
                    .otpId(otpId)
                    .otpCode(otpCode)
                    .username(request.getUsername())
                    .sendTo(request.getEmail())
                    .fullName(request.getFullName())
                    .password(password)
                    .salt(salt)
                    .userId(userId)
                    .issuedAt(issuedAt)
                    .expiredAt(expiredAt)
                    .build();
            return commandGateway.send(command)
                    .thenApply(it -> {
                        var response = OtpResponse.builder()
                                .otpId(otpId)
                                .maskedEmail(MaskUtil.maskEmail(request.getEmail()))
                                .expiredAt(expiredAt.toString())
                                .expiredIn(authConfig.getRegisterOtp().getExpirationTime())
                                .build();
                        return new CommonResponse(SuccessCode.SUCCESS, response);
                    })
                    .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
        } finally {
            // Clear password in memory for security
            Arrays.fill(password, Byte.MIN_VALUE);
        }
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
                    var response = OtpResponse.builder()
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
                    var response = OtpResponse.builder()
                            .otpId(otpId)
                            .maskedEmail(MaskUtil.maskEmail(request.getEmail()))
                            .expiredAt(expiredAt.toString())
                            .expiredIn(authConfig.getForgotOtp().getExpirationTime())
                            .build();
                    return new CommonResponse(SuccessCode.SUCCESS, response);
                })
                .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @Override
    public CompletableFuture<CommonResponse> confirmForgotOtp(ConfirmForgotOtpRequest request) {
        var password = new byte[0];
        try {
            log.info("Confirm OTP for forgot password with request: {}", request);

            // Get password and salt
            var salt = GeneratorUtil.generateSalt();
            password = EncryptUtil.generatePassword(request.getPassword(), userRSAEncryption.getPrivateKey(), salt);

            var confirmCommand = ConfirmForgotOtpCommand.builder()
                    .otpId(request.getOtpId())
                    .otpCode(request.getOtpCode())
                    .password(password)
                    .salt(salt)
                    .build();
            return commandGateway.send(confirmCommand)
                    .thenApply(it -> new CommonResponse(SuccessCode.SUCCESS))
                    .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
        } finally {
            // Clear password in memory for security
            Arrays.fill(password, Byte.MIN_VALUE);
        }
    }

    @Override
    public CompletableFuture<CommonResponse> resendForgotOtp(ResendForgotOtpRequest request) {
        log.info("Resend OTP for forgot password with request: {}", request);
        var resendCommand = ResendForgotOtpCommand.builder()
                .otpId(request.getOtpId())
                .build();
        return commandGateway.send(resendCommand)
                .thenApply(it -> {
                    var otp = queryGateway.query(new GetOtpQuery(request.getOtpId()), OtpEntity.class).join();
                    var response = OtpResponse.builder()
                            .otpId(otp.getOtpId())
                            .maskedEmail(MaskUtil.maskEmail(otp.getSendTo()))
                            .expiredAt(otp.getExpiredAt().toString())
                            .expiredIn(authConfig.getForgotOtp().getExpirationTime())
                            .build();
                    return new CommonResponse(SuccessCode.SUCCESS, response);
                })
                .exceptionally(t -> new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @Override
    public CommonResponse login(LoginRequest request) {
        PasswordEntity password = null;
        byte[] inputPassword = new byte[0];
        try {
            log.info("Login with request: {}", request);

            var user = queryGateway.query(new GetUserByUsernameQuery(request.getUsername()), UserEntity.class).join();
            if (user == null) {
                log.error("User: {} not found", request.getUsername());
                throw new NotFoundException("User not found");
            }
            if (!UserStatus.ACTIVE.getStatus().equals(user.getStatus())) {
                log.error("User: {} is not active", user.getUserId());
                throw new NotFoundException("User is not active");
            }

            password = queryGateway.query(new GetPasswordByUserIdQuery(user.getUserId()), PasswordEntity.class).join();
            if (password == null) {
                log.error("Password not found for user: {}", user.getUserId());
                throw new NotFoundException("Password not found");
            }

            // Validate password
            inputPassword = EncryptUtil.generatePassword(request.getPassword(), userRSAEncryption.getPrivateKey(), password.getSalt());
            if (!Arrays.equals(inputPassword, password.getPassword())) {
                log.error("Password not match for user: {}", user.getUserId());
                throw new PasswordNotMatchException("Password not match");
            }

            // Generate accessToken and refreshToken
            var accessToken = TokenUtil.generateToken(authConfig.getAccessToken(), userRSAEncryption.getPrivateKey(), TokenType.ACCESS_TOKEN, user);
            var refreshToken = TokenUtil.generateToken(authConfig.getRefreshToken(), userRSAEncryption.getPrivateKey(), TokenType.REFRESH_TOKEN, user);

            var loginResponse = LoginResponse.builder()
                    .user(user)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return new CommonResponse(SuccessCode.SUCCESS, loginResponse);
        } finally {
            // Clear password in memory for security
            Arrays.fill(inputPassword, Byte.MIN_VALUE);
            if (password != null) {
                Arrays.fill(password.getPassword(), Byte.MIN_VALUE);
            }
        }
    }
}
