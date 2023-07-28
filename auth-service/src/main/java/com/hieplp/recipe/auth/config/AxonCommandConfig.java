package com.hieplp.recipe.auth.config;

import com.hieplp.recipe.auth.domain.command.interceptor.OtpDispatchInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonCommandConfig {
    @Autowired
    void commandBus(CommandBus commandBus, OtpDispatchInterceptor otpDispatchInterceptor) {
        // noinspection resource
        commandBus.registerDispatchInterceptor(otpDispatchInterceptor);
    }
}