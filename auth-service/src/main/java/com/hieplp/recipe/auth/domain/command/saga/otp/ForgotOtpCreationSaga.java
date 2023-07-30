package com.hieplp.recipe.auth.domain.command.saga.otp;

import com.hieplp.recipe.auth.domain.command.commands.otp.create.CancelOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CompleteOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.create.ForgotOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class ForgotOtpCreationSaga {

    private static final String OTP_ID = "otpId";
    private static final String LOG_ID = "logId";

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient OtpHelper otpHelper;


    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(ForgotOtpCreatedEvent event) {
        try {
            log.info("Saga handles forgot otp created event: {}", event);

            final var logId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith(LOG_ID, logId);

            otpHelper.sendForgotOtp(event.getOtpId(), logId, event.getSendTo());
        } catch (Exception e) {
            log.error("Error when handle forgot otp created event:", e);
            cancelOtp(event.getOtpId());
        }
    }

    @SagaEventHandler(associationProperty = LOG_ID)
    private void handle(EmailCompletedEvent event) {
        try {
            log.info("Saga handles email completed event: {}", event);
            var completeOtpCreationCommand = CompleteOtpCreationCommand.builder()
                    .otpId(event.getReferenceId())
                    .build();
            commandGateway.send(completeOtpCreationCommand);
        } catch (Exception e) {
            log.error("Error when handle email completed event:", e);
            cancelOtp(event.getReferenceId());
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(OtpCreationCompletedEvent event) {
        log.info("Saga handles forgot otp creation completed event: {}", event);
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(OtpCreationCanceledEvent event) {
        log.info("Saga handles forgot otp creation canceled event: {}", event);
        SagaLifecycle.end();
    }

    private void cancelOtp(String otpId) {
        var cancelOtpCreateCommand = CancelOtpCreationCommand.builder()
                .otpId(otpId)
                .build();
        commandGateway.send(cancelOtpCreateCommand);
    }
}