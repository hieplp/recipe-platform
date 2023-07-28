package com.hieplp.recipe.auth.domain.command.eventhandler.register;

import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtpRecord;
import com.hieplp.recipe.auth.domain.command.commands.register.CancelRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.event.register.RegisterOtpCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.register.RegisterOtpCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.register.RegisterOtpCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterOtpEventHandler {

    private final OtpRepo otpRepo;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(RegisterOtpCreatedEvent event) {
        try {
            log.info("Handle registration otp created event: {}", event);
            var otpRecord = new OtpRecord();
            otpRecord.from(event);
            otpRecord
                    .setSendto(event.getEmail())
                    .setCreatedat(LocalDateTime.now())
                    .setCreatedby(event.getUserId())
                    .setModifiedat(LocalDateTime.now())
                    .setModifiedby(event.getUserId());
            otpRepo.save(otpRecord);
        } catch (Exception e) {
            log.error("Error when handle registration otp created event: {}", e.getMessage(), e);
            cancelOtp(event.getOtpId(), event.getUserId());
        }
    }

    @EventHandler
    private void handle(RegisterOtpCompletedEvent event) {
        try {
            log.info("Handle registration otp completed event: {}", event);
            var otpRecord = new OtpRecord()
                    .setOtpid(event.getOtpId())
                    .setStatus(event.getStatus())
                    .setModifiedby(event.getUserId())
                    .setModifiedat(LocalDateTime.now());
            otpRepo.updateNotNull(otpRecord);
        } catch (Exception e) {
            log.error("Error when handle registration otp completed event: {}", e.getMessage(), e);
            cancelOtp(event.getOtpId(), event.getUserId());
        }
    }

    @EventHandler
    private void handle(RegisterOtpCanceledEvent event) {
        try {
            log.info("Handle registration otp canceled event: {}", event);
            var otpRecord = new OtpRecord()
                    .setOtpid(event.getOtpId())
                    .setStatus(event.getStatus())
                    .setModifiedby(event.getUserId())
                    .setModifiedat(LocalDateTime.now());
            otpRepo.updateNotNull(otpRecord);
        } catch (Exception e) {
            // Ignore
            log.error("Error when handle registration otp canceled event: {}", e.getMessage(), e);
        }
    }

    private void cancelOtp(String otpId, String userId) {
        var cancelOtpCommand = CancelRegisterOtpCommand.builder()
                .otpId(otpId)
                .userId(userId)
                .build();
        commandGateway.send(cancelOtpCommand);
    }
}
