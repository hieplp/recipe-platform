package com.hieplp.recipe.auth.domain.command.eventhandler.otp;

import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtpRecord;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.*;
import com.hieplp.recipe.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpConfirmationEventHandler {

    private final OtpRepo otpRepo;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(RegisterOtpConfirmedEvent event) {
        log.info("Handle registration otp confirmed event: {}", event);
        updateOtp(event);
    }

    @EventHandler
    private void handle(ForgotOtpConfirmedEvent event) {
        log.info("Handle forgot otp confirmed event: {}", event);
        updateOtp(event);
    }

    @EventHandler
    private void handle(OtpConfirmationCompletedEvent event) {
        log.info("Handle registration otp confirmation completed event: {}", event);
    }

    @EventHandler
    private void handle(OtpConfirmationCanceledEvent event) {
        log.info("Handle registration otp confirmation canceled event: {}", event);
        var otpRecord = new OtpRecord()
                .setOtpid(event.getOtpId())
                .setStatus(event.getStatus())
                .setModifiedat(event.getModifiedAt());
        otpRepo.updateNotNull(otpRecord);
    }

    private void updateOtp(OtpConfirmedEvent event) {
        var otpRecord = new OtpRecord()
                .setOtpid(event.getOtpId())
                .setStatus(event.getStatus())
                .setModifiedat(DateUtil.now());
        otpRepo.updateNotNull(otpRecord);
    }
}
