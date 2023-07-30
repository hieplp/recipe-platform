package com.hieplp.recipe.auth.domain.command.helper.impl;

import com.hieplp.recipe.auth.domain.command.event.otp.create.ForgotOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.common.command.commands.notification.email.SendEmailCommand;
import com.hieplp.recipe.common.enums.notification.TemplateAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpHelperImpl implements OtpHelper {

    private final QueryGateway queryGateway;
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

        // Get last event
        var lastEvent = eventStore.readEvents(otpId).asStream().reduce((first, second) -> second).orElse(null);
        if (lastEvent == null) {
            throw new IllegalArgumentException("Otp not found");
        }

        var otp = (ForgotOtpCreatedEvent) lastEvent.getPayload();
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
                .referenceId(otp.getOtpId())
                .createdBy(sentBy)
                .build());
    }
}
