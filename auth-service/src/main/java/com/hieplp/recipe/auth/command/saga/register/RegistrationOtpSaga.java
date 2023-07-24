package com.hieplp.recipe.auth.command.saga.register;

import com.hieplp.recipe.auth.command.commands.CancelRegistrationOtpCommand;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCanceledEvent;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCompletedEvent;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class RegistrationOtpSaga {

    private static final String OTP_ID = "otpId";
    private static final String USER_ID = "userId";

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    public void handle(RegistrationOtpCreatedEvent event) {
        try {
            log.info("Saga handles registration otp created event: {}", event);

            //
            SagaLifecycle.associateWith(OTP_ID, event.getOtpId());

            // Validate quota

        } catch (Exception e) {
            log.error("Error when handle registration otp created event: {}", event, e);
            cancelOtp(event.getOtpId(), event.getUserId());
        }
    }

    // -------------------------------------------------------------------------
    // XXX Complete otp
    // -------------------------------------------------------------------------
    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegistrationOtpCompletedEvent event) {
        log.info("Saga handles registration otp completed event: {}", event);
        SagaLifecycle.end();
    }

    // -------------------------------------------------------------------------
    // XXX Cancel otp
    // -------------------------------------------------------------------------
    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegistrationOtpCanceledEvent event) {
        log.info("Saga handles registration otp canceled event: {}", event);
        SagaLifecycle.end();
    }

    // -------------------------------------------------------------------------
    // XXX Private methods
    // -------------------------------------------------------------------------
    private void cancelOtp(String otpId, String userId) {
        var cancelOtpCommand = CancelRegistrationOtpCommand.builder()
                .otpId(otpId)
                .userId(userId)
                .build();
        commandGateway.send(cancelOtpCommand);
    }
}
