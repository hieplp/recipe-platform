package com.hieplp.recipe.auth.command.service.impl;

import com.hieplp.recipe.auth.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.command.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.response.auth.GenerateRegisterOtpResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.VerifyRegisterOtpResponse;
import com.hieplp.recipe.auth.command.service.AuthCommandService;
import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.auth.query.queries.GetTodayOtpQuotaQuery;
import com.hieplp.recipe.common.command.commands.notification.email.SendEmailCommand;
import com.hieplp.recipe.common.enums.notification.TemplateAction;
import com.hieplp.recipe.common.enums.otp.OtpType;
import com.hieplp.recipe.common.exception.auth.ExceededOtpQuotaException;
import com.hieplp.recipe.common.exception.user.DuplicatedUsernameException;
import com.hieplp.recipe.common.query.queries.user.CheckEmailExistenceQuery;
import com.hieplp.recipe.common.query.queries.user.CheckUsernameExistenceQuery;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private static final int DEFAULT_OTP_LENGTH = 6;

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

        // Send OTP
        var params = new HashMap<String, String>();
        params.put("otpCode", GeneratorUtil.generateOTP(DEFAULT_OTP_LENGTH));
        var res = commandGateway.sendAndWait(SendEmailCommand.builder()
                .action(TemplateAction.REGISTER.getAction())
                .email(request.getEmail())
                .params(params)
                .createdBy(request.getUsername())
                .build());
        log.info("Send email result: {}", res);

        //
        final var otpId = UUID.randomUUID().toString();
        final var otpCode = GeneratorUtil.generateOTP(DEFAULT_OTP_LENGTH);
        final var userId = UUID.randomUUID().toString();

        //
//        final var command = CreateRegistrationOtpCommand.builder()
//                .otpId(otpId)
//                .otpCode(otpCode)
//                .username(request.getUsername())
//                .email(request.getEmail())
//                .userId(userId)
//                .build();
//        var result = commandGateway.sendAndWait(command);

        return null;
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
