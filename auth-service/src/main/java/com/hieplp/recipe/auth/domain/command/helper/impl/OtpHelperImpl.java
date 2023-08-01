package com.hieplp.recipe.auth.domain.command.helper.impl;

import com.hieplp.recipe.auth.domain.command.commands.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResentEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.common.command.commands.notification.email.SendEmailCommand;
import com.hieplp.recipe.common.enums.IdLength;
import com.hieplp.recipe.common.enums.notification.TemplateAction;
import com.hieplp.recipe.common.enums.otp.OtpHistoryStatus;
import com.hieplp.recipe.common.enums.otp.OtpHistoryType;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpHelperImpl implements OtpHelper {

    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    @Override
    public void sendRegisterOtp(String otpId, String logId, String sentBy) {
        sendOtp(otpId, logId, sentBy, TemplateAction.REGISTER);
    }

    @Override
    public void sendForgotOtp(String otpId, String logId, String sentBy) {
        sendOtp(otpId, logId, sentBy, TemplateAction.FORGOT);
    }

    @Override
    public void sendOtp(String otpId, String logId, String sentBy, TemplateAction action) {
        log.debug("Send otp via email: {} with logId:{}, sentBy: {} and action: {}", otpId, logId, sentBy, action);

        // Get first event of otp from event store.
        // TODO: Sometimes, otp haven't been stored in database or redis yet.
        //       So, I need to get it from event store.
        //       Change this to reading from parameters or something else.
        var lastEvent = eventStore.readEvents(otpId)
                // Only use otpCode, sendTo from otp and they aren't changed since created
                // So, I can use createdEvent for all cases
                .filter(event -> event.getPayload() instanceof OtpCreatedEvent)
                .asStream().reduce((first, second) -> second).orElse(null);
        if (lastEvent == null) {
            throw new IllegalArgumentException("Otp not found");
        }

        // Get otp from last event
        var otp = (OtpCreatedEvent) lastEvent.getPayload();
        if (otp == null) {
            throw new IllegalArgumentException("Otp not found");
        }

        // Init email params
        var params = new HashMap<String, String>();
        params.put("otpCode", otp.getOtpCode());

        // Send OTP via email
        commandGateway.send(SendEmailCommand.builder()
                .logId(logId)
                .action(action.getAction())
                .sendTo(otp.getSendTo())
                .params(params)
                .createdBy(sentBy)
                .build());
    }

    @Override
    public void sendCreateOtpHistoryCommand(String otpHistoryId, OtpResentEvent event) {
        log.debug("Send create otp history command: {}", event);
        sendCreateOtpHistoryCommand(CreateOtpHistoryCommand.builder()
                .otpHistoryId(otpHistoryId)
                .otpId(event.getOtpId())
                .otpCode(event.getOtpCode())
                .type(OtpHistoryType.RESEND.getType())
                .status(OtpHistoryStatus.INIT.getStatus())
                .createdBy("anonymous")
                .build());
    }

    @Override
    public void sendCreateOtpHistoryCommand(CreateOtpHistoryCommand command) {
        if (command.getOtpHistoryId() == null) {
            command.setOtpHistoryId(GeneratorUtil.generateId(IdLength.OTP_ID));
        }
        commandGateway.send(command);
    }
}
