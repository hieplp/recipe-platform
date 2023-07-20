package com.hieplp.recipe.auth.service.impl;

import com.hieplp.recipe.auth.service.AuthService;
import com.hieplp.recipe.common.grpc.user.DoesUsernameExistRequest;
import com.hieplp.recipe.common.grpc.user.UserServiceGrpc;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EurekaClient eurekaClient;

    @Override
    public boolean doesUsernameExist(String username) {
        log.info("Check if username {} exists", username);

        final var instance = eurekaClient.getNextServerFromEureka("user-service", false);
        final var channel = io.grpc.ManagedChannelBuilder.forAddress(instance.getIPAddr(), instance.getPort())
                .usePlaintext()
                .build();
        final var stub = UserServiceGrpc.newBlockingStub(channel);
        final var request = DoesUsernameExistRequest.newBuilder()
                .setUserName(username)
                .build();
        final var response = stub.doesUsernameExist(request);

        return response.getExists();
    }
}