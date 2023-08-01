package com.hieplp.recipe.auth.domain.command.saga.otp.confirm;

import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.CancelOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.OtpConfirmationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.OtpConfirmationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class OtpConfirmationSaga {

    static final String OTP_ID = "otpId";
    static final String USER_ID = "userId";

    @Autowired
    transient CommandGateway commandGateway;
    @Autowired
    transient QueryGateway queryGateway;
    @Autowired
    transient OtpHelper otpHelper;

    String userId;
    String otpId;

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(OtpConfirmationCompletedEvent event) {
        log.info("Saga handles otp confirmation completed event: {}", event);
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(OtpConfirmationCanceledEvent event) {
        log.info("Saga handles otp confirmation canceled event: {}", event);
        SagaLifecycle.end();
    }

    void cancelOtpConfirmation() {
        log.info("Saga cancels otp confirmation: {}", this.otpId);
        var cancelOtpConfirmationCommand = CancelOtpConfirmationCommand.builder()
                .otpId(this.otpId)
                .build();
        commandGateway.send(cancelOtpConfirmationCommand);
    }
}
