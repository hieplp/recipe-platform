package com.hieplp.recipe.auth.service.impl;

import com.hieplp.recipe.auth.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.payload.response.auth.VerifyRegisterOtpResponse;
import com.hieplp.recipe.auth.service.AuthService;
import com.hieplp.recipe.common.grpc.user.DoesUsernameExistRequest;
import com.hieplp.recipe.common.grpc.user.UserServiceGrpc;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EurekaClient eurekaClient;

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub stub;

    @Override
    public boolean doesUsernameExist(String username) {
        log.info("Check if username {} exists", username);

        final var request = DoesUsernameExistRequest.newBuilder()
                .setUserName(username)
                .build();
        final var response = stub.doesUsernameExist(request);

        return response.getExists();
    }

    @Override
    public GenerateRegisterOtpRequest generateRegisterOtp(GenerateRegisterOtpRequest request) {
        log.info("Generate OTP for register with request: {}", request);

        // Check if email quota is reached

        // Check if username exist or not

        // Check if email exist or not


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
