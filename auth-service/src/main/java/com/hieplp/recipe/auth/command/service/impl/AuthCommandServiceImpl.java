package com.hieplp.recipe.auth.command.service.impl;

import com.hieplp.recipe.auth.command.commands.CreateRegistrationOtpCommand;
import com.hieplp.recipe.auth.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.command.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.response.auth.GenerateRegisterOtpResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.VerifyRegisterOtpResponse;
import com.hieplp.recipe.auth.command.service.AuthCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private final CommandGateway commandGateway;

    @Override
    public GenerateRegisterOtpResponse generateRegisterOtp(GenerateRegisterOtpRequest request) {
        log.info("Generate OTP for register with request: {}", request);
        final var otpId = UUID.randomUUID().toString();
        final var command = CreateRegistrationOtpCommand.builder()
                .otpId(otpId)
                .username(request.getUsername())
                .email(request.getEmail())
                .build();
        var result = commandGateway.sendAndWait(command);
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
