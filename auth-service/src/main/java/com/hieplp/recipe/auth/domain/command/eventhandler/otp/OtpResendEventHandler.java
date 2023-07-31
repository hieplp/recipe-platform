package com.hieplp.recipe.auth.domain.command.eventhandler.otp;

import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResendCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResendCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.RegisterOtpResentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpResendEventHandler {

    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(RegisterOtpResentEvent event) {
        log.info("Handle register otp resent event: {}", event);
    }

    @EventHandler
    private void handle(OtpResendCompletedEvent event) {
        log.info("Handle register otp resent completed event: {}", event);
    }

    @EventHandler
    private void handle(OtpResendCanceledEvent event) {
        log.info("Handle register otp resent canceled event: {}", event);
    }
}
