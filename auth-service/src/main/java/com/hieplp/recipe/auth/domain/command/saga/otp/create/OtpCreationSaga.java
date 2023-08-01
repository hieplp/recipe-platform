package com.hieplp.recipe.auth.domain.command.saga.otp.create;

import com.hieplp.recipe.auth.domain.command.commands.otp.create.CancelOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CompleteOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.common.command.events.notification.email.EmailCanceledEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class OtpCreationSaga {
    static final String OTP_ID = "otpId";
    static final String LOG_ID = "logId";
    static final String USER_ID = "userId";

    // transient indicates that the field should not be part of the serialization process
    @Autowired
    transient CommandGateway commandGateway;
    @Autowired
    transient QueryGateway queryGateway;
    @Autowired
    transient OtpHelper otpHelper;

    String otpId;
    String userId;
    String logId;

    @SagaEventHandler(associationProperty = LOG_ID)
    void handle(EmailCompletedEvent event) {
        try {
            log.info("Saga handles email completed event: {}", event);
            commandGateway.send(CompleteOtpCreationCommand.builder()
                    .otpId(this.otpId)
                    .build());
        } catch (Exception e) {
            log.error("Error when handle email completed event: {}", event, e);
            cancelOtpCreation();
        }
    }

    @SagaEventHandler(associationProperty = LOG_ID)
    void handle(EmailCanceledEvent event) {
        log.info("Saga handles email canceled event: {}", event);
        cancelOtpCreation();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(OtpCreationCompletedEvent event) {
        log.info("Saga handles registration otp completed event: {}", event);
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(OtpCreationCanceledEvent event) {
        log.info("Saga handles registration otp canceled event: {}", event);
        SagaLifecycle.end();
    }

    void cancelOtpCreation() {
        commandGateway.send(CancelOtpCreationCommand.builder()
                .otpId(this.otpId)
                .build());
    }
}
