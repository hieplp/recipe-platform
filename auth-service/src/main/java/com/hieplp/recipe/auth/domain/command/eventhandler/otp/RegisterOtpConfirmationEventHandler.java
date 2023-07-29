package com.hieplp.recipe.auth.domain.command.eventhandler.otp;

import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtpRecord;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterOtpConfirmationEventHandler {

    private final OtpRepo otpRepo;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(RegisterOtpConfirmedEvent event) {
        log.info("Handle registration otp confirmed event: {}", event);
        var otpRecord = new OtpRecord()
                .setOtpid(event.getOtpId())
                .setStatus(event.getStatus())
                .setModifiedby(event.getUserId())
                .setModifiedat(LocalDateTime.now());
        otpRepo.updateNotNull(otpRecord);
    }

    @EventHandler
    private void handle(RegisterOtpConfirmationCompletedEvent event) {
        log.info("Handle registration otp confirmation completed event: {}", event);
    }

    @EventHandler
    private void handle(RegisterOtpConfirmationCanceledEvent event) {
        log.info("Handle registration otp confirmation canceled event: {}", event);
        var otpRecord = new OtpRecord()
                .setOtpid(event.getOtpId())
                .setStatus(event.getStatus())
                .setModifiedby(event.getModifiedBy())
                .setModifiedat(LocalDateTime.now());
        otpRepo.updateNotNull(otpRecord);
    }
}
