package com.hieplp.recipe.auth.domain.command.eventhandler.otp;

import com.hieplp.recipe.auth.common.repository.OtpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpConfirmationEventHandler {
    private final OtpRepo otpRepo;
    private final CommandGateway commandGateway;
}
