package com.hieplp.recipe.auth.domain.command.saga.otp;

import com.hieplp.recipe.auth.domain.command.commands.otp.create.CancelOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CompleteOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.user.create.CreateTempUserCommand;
import com.hieplp.recipe.auth.domain.command.event.user.TempUserCompletedEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.common.command.events.notification.email.EmailCanceledEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class RegisterOtpCreationSaga {

    private static final String OTP_ID = "otpId";
    private static final String LOG_ID = "logId";
    private static final String USER_ID = "userId";

    // transient indicates that the field should not be part of the serialization process
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;
    @Autowired
    private transient OtpHelper otpHelper;


    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpCreatedEvent event) {
        try {
            log.info("Saga handles registration otp created event: {}", event);

            SagaLifecycle.associateWith(USER_ID, event.getUserId());

            var createTempUserCommand = CreateTempUserCommand.builder()
                    .userId(event.getUserId())
                    .username(event.getUsername())
                    .email(event.getEmail())
                    .fullName(event.getFullName())
                    .password(event.getPassword())
                    .createdBy(event.getUserId())
                    .referenceId(event.getOtpId())
                    .build();
            commandGateway.send(createTempUserCommand);
        } catch (Exception e) {
            log.error("Error when handle registration otp created event: {}", event, e);
            cancelOtp(event.getOtpId());
        }
    }

    // -------------------------------------------------------------------------
    // XXX Temporary user is completely created
    // -------------------------------------------------------------------------
    @SagaEventHandler(associationProperty = USER_ID)
    private void handle(TempUserCompletedEvent event) {
        try {
            log.info("Saga handles temp user completed event: {}", event);

            final var logId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith(LOG_ID, logId);

            otpHelper.sendRegisterOtp(event.getReferenceId(), logId, event.getUserId());
        } catch (Exception e) {
            log.error("Error when handle temp user completed event: {}", event, e);
            cancelOtp(event.getReferenceId());
        }
    }

    // -------------------------------------------------------------------------
    // XXX Events from email
    // -------------------------------------------------------------------------
    @SagaEventHandler(associationProperty = LOG_ID)
    private void handle(EmailCompletedEvent event) {
        try {
            log.info("Saga handles email completed event: {}", event);
            commandGateway.send(CompleteOtpCreationCommand.builder()
                    .otpId(event.getReferenceId())
                    .build());
        } catch (Exception e) {
            log.error("Error when handle email completed event: {}", event, e);
            cancelOtp(event.getReferenceId());
        }
    }

    @SagaEventHandler(associationProperty = LOG_ID)
    private void handle(EmailCanceledEvent event) {
        log.info("Saga handles email canceled event: {}", event);
        cancelOtp(event.getReferenceId());
    }

    // -------------------------------------------------------------------------
    // XXX Complete otp
    // -------------------------------------------------------------------------
    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpCompletedEvent event) {
        log.info("Saga handles registration otp completed event: {}", event);
        SagaLifecycle.end();
    }

    // -------------------------------------------------------------------------
    // XXX Cancel otp
    // -------------------------------------------------------------------------
    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpCanceledEvent event) {
        log.info("Saga handles registration otp canceled event: {}", event);
        SagaLifecycle.end();
    }

    // -------------------------------------------------------------------------
    // XXX Private methods
    // -------------------------------------------------------------------------
    private void cancelOtp(String otpId) {
        commandGateway.send(CancelOtpCreationCommand.builder()
                .otpId(otpId)
                .build());
    }
}
