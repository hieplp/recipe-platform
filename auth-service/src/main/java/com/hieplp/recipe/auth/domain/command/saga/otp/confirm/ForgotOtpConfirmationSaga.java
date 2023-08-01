package com.hieplp.recipe.auth.domain.command.saga.otp.confirm;

import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.CompleteOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.ForgotOtpConfirmedEvent;
import com.hieplp.recipe.common.command.commands.password.update.UpdatePasswordCommand;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdateCanceledEvent;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdateCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class ForgotOtpConfirmationSaga extends OtpConfirmationSaga {
    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(ForgotOtpConfirmedEvent event) {
        try {
            log.info("Saga handles forgot otp confirmed event: {}", event);

            // Update saga state
            this.otpId = event.getOtpId();
            this.userId = event.getUserId();

            // Associate saga with aggregate
            SagaLifecycle.associateWith(USER_ID, this.userId);

            // Send event to update user's password
            var updatePasswordCommand = UpdatePasswordCommand.builder()
                    .userId(this.userId)
                    .password(event.getPassword())
                    .salt(event.getSalt())
                    .build();
            commandGateway.send(updatePasswordCommand);
        } catch (Exception e) {
            log.error("Error when handle forgot otp confirmed event:", e);
            cancelOtpConfirmation();
        }
    }

    @SagaEventHandler(associationProperty = USER_ID)
    void handle(PasswordUpdateCompletedEvent event) {
        try {
            log.info("Saga handles password update completed event: {}", event);
            var completeCommand = CompleteOtpConfirmationCommand.builder()
                    .otpId(this.otpId)
                    .build();
            commandGateway.send(completeCommand);
        } catch (Exception e) {
            log.error("Error when handle password update completed event:", e);
            cancelOtpConfirmation();
        }
    }

    @SagaEventHandler(associationProperty = USER_ID)
    void handle(PasswordUpdateCanceledEvent event) {
        try {
            log.info("Saga handles password update canceled event: {}", event);
            cancelOtpConfirmation();
        } catch (Exception e) {
            log.error("Error when handle password update canceled event:", e);
            cancelOtpConfirmation();
        }
    }
}
