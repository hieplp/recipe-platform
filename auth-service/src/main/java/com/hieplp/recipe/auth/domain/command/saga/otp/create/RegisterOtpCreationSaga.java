package com.hieplp.recipe.auth.domain.command.saga.otp.create;

import com.hieplp.recipe.auth.domain.command.commands.user.create.CreateTempUserCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.create.RegisterOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.user.TempUserCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.UUID;

@Saga
@Slf4j
public class RegisterOtpCreationSaga extends OtpCreationSaga {
    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(RegisterOtpCreatedEvent event) {
        try {
            log.info("Saga handles registration otp created event: {}", event);

            this.otpId = event.getOtpId();
            this.userId = event.getUserId();

            SagaLifecycle.associateWith(USER_ID, this.userId);

            var createTempUserCommand = CreateTempUserCommand.builder()
                    .userId(this.userId)
                    .username(event.getUsername())
                    .email(event.getSendTo())
                    .fullName(event.getFullName())
                    .password(event.getPassword())
                    .salt(event.getSalt())
                    .createdBy(this.userId)
                    .referenceId(this.otpId)
                    .build();
            commandGateway.send(createTempUserCommand);
        } catch (Exception e) {
            log.error("Error when handle registration otp created event: {}", event, e);
            cancelOtpCreation();
        }
    }

    @SagaEventHandler(associationProperty = USER_ID)
    private void handle(TempUserCompletedEvent event) {
        try {
            log.info("Saga handles temp user completed event: {}", event);

            this.logId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith(LOG_ID, this.logId);

            otpHelper.sendRegisterOtp(event.getReferenceId(), this.logId, event.getUserId());
        } catch (Exception e) {
            log.error("Error when handle temp user completed event: {}", event, e);
            cancelOtpCreation();
        }
    }
}
