package com.hieplp.recipe.auth.domain.command.eventhandler.otp;

import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtpRecord;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CancelOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.create.ForgotOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.RegisterOtpCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpCreationEventHandler {

    private final OtpRepo otpRepo;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(RegisterOtpCreatedEvent event) {
        try {
            log.info("Handle registration otp created event: {}", event);
            var otpRecord = new OtpRecord();
            otpRecord.from(event);
            otpRecord
                    .setSendto(event.getSendTo())
                    .setCreatedby(event.getUserId())
                    .setCreatedat(event.getCreatedAt())
                    .setModifiedby(event.getUserId())
                    .setModifiedat(event.getCreatedAt());
            otpRepo.save(otpRecord);
        } catch (Exception e) {
            log.error("Error when handle registration otp created event:", e);
            cancelOtp(event.getOtpId());
        }
    }

    @EventHandler
    private void handle(ForgotOtpCreatedEvent event) {
        try {
            log.info("Handle forgot otp created event: {}", event);
            var otpRecord = new OtpRecord();
            otpRecord.from(event);
            otpRecord
                    .setSendto(event.getSendTo())
                    .setCreatedat(event.getCreatedAt())
                    .setCreatedby(event.getCreatedBy())
                    .setModifiedat(event.getCreatedAt())
                    .setModifiedby(event.getCreatedBy());
            otpRepo.save(otpRecord);
        } catch (Exception e) {
            log.info("Error when handle forgot otp created event:", e);
            cancelOtp(event.getOtpId());
        }
    }

    @EventHandler
    private void handle(OtpCreationCompletedEvent event) {
        try {
            log.info("Handle otp creation completed event: {}", event);
            var otpRecord = new OtpRecord()
                    .setOtpid(event.getOtpId())
                    .setStatus(event.getStatus())
                    .setModifiedat(event.getModifiedAt());
            otpRepo.updateNotNull(otpRecord);
        } catch (Exception e) {
            log.error("Error when handle otp creation completed event:", e);
            cancelOtp(event.getOtpId());
        }
    }

    @EventHandler
    private void handle(OtpCreationCanceledEvent event) {
        try {
            log.info("Handle otp creation canceled event: {}", event);
            var otpRecord = new OtpRecord()
                    .setOtpid(event.getOtpId())
                    .setStatus(event.getStatus())
                    .setModifiedat(event.getModifiedAt());
            otpRepo.updateNotNull(otpRecord);
        } catch (Exception e) {
            log.error("Error when handle otp creation canceled event:", e);
            // ignore
        }
    }

    private void cancelOtp(String otpId) {
        var cancelOtpCreateCommand = CancelOtpCreationCommand.builder()
                .otpId(otpId)
                .build();
        commandGateway.send(cancelOtpCreateCommand);
    }
}
