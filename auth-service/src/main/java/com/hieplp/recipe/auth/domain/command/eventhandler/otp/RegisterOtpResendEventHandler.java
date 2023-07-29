package com.hieplp.recipe.auth.domain.command.eventhandler.otp;

import com.hieplp.recipe.auth.domain.command.commands.otp.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.register.resend.RegisterOtpResentEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCompletedEvent;
import com.hieplp.recipe.common.enums.otp.OtpHistoryStatus;
import com.hieplp.recipe.common.enums.otp.OtpHistoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterOtpResendEventHandler {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(RegisterOtpResentEvent event) {
        try {
            log.info("Handle register otp resent event: {}", event);
            var createOtpHistoryCommand = CreateOtpHistoryCommand.builder()
                    .otpId(event.getOtpId())
                    .otpCode(event.getOtpCode())
                    .type(OtpHistoryType.RESEND.getType())
                    .status(OtpHistoryStatus.INIT.getStatus())
                    .createdBy("anonymous")
                    .build();
            commandGateway.sendAndWait(createOtpHistoryCommand);
        } catch (Exception e) {
            log.error("Error when handle register otp resent event:", e);
        }
    }

    @EventHandler
    private void handle(RegisterOtpResendCompletedEvent event) {
        log.info("Handle register otp resent completed event: {}", event);
    }

    @EventHandler
    private void handle(RegisterOtpResendCanceledEvent event) {
        log.info("Handle register otp resent canceled event: {}", event);
    }
}
