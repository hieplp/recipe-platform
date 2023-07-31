package com.hieplp.recipe.auth.domain.command.eventhandler.otp;

import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtpRecord;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.OtpConfirmationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.OtpConfirmationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.RegisterOtpConfirmedEvent;
import com.hieplp.recipe.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

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
                .setModifiedat(DateUtil.now());
        otpRepo.updateNotNull(otpRecord);
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
                .setModifiedby(event.getModifiedBy())
                .setModifiedat(DateUtil.now());
        otpRepo.updateNotNull(otpRecord);
    }
}
