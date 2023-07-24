package com.hieplp.recipe.auth.command.eventhandler.register;

import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCanceledEvent;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCompletedEvent;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCreatedEvent;
import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtpRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationOtpEventHandler {

    private final OtpRepo otpRepo;

    @EventHandler
    private void handle(RegistrationOtpCreatedEvent event) {
        log.info("Handle registration otp created event: {}", event);
        var otpRecord = new OtpRecord()
                .setOtpid(event.getOtpId())
                .setOtpcode(event.getOtpCode())
                .setSendto(event.getEmail())
                .setStatus(event.getStatus())
                .setCreatedat(LocalDateTime.now())
                .setCreatedby(event.getUserId())
                .setModifiedat(LocalDateTime.now())
                .setModifiedby(event.getUserId());
        otpRepo.save(otpRecord);
    }

    @EventHandler
    private void handle(RegistrationOtpCompletedEvent event) {
        log.info("Handle registration otp completed event: {}", event);
        var otpRecord = new OtpRecord()
                .setOtpid(event.getOtpId())
                .setStatus(event.getStatus())
                .setModifiedby(event.getUserId())
                .setModifiedat(LocalDateTime.now());
        otpRepo.updateNotNull(otpRecord);
    }

    @EventHandler
    private void handle(RegistrationOtpCanceledEvent event) {
        log.info("Handle registration otp canceled event: {}", event);
        var otpRecord = new OtpRecord()
                .setOtpid(event.getOtpId())
                .setStatus(event.getStatus())
                .setModifiedby(event.getUserId())
                .setModifiedat(LocalDateTime.now());
        otpRepo.updateNotNull(otpRecord);
    }

    @ExceptionHandler
    public void handleAll(Exception exception) {
        // Handles all exceptions thrown within this component generically
        log.error("Error when handle event: {}", exception.getMessage(), exception);
    }
}
