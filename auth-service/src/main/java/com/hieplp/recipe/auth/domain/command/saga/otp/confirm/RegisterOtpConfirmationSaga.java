package com.hieplp.recipe.auth.domain.command.saga.otp.confirm;

import com.hieplp.recipe.auth.common.entity.TempUserEntity;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.CompleteOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.RegisterOtpConfirmedEvent;
import com.hieplp.recipe.auth.domain.query.queries.tempuser.GetTempUserByOtpIdQuery;
import com.hieplp.recipe.common.command.commands.user.create.CreateUserCommand;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCanceledEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCompletedEvent;
import com.hieplp.recipe.common.enums.IdLength;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class RegisterOtpConfirmationSaga extends OtpConfirmationSaga {
    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    public void handle(RegisterOtpConfirmedEvent event) {
        try {
            log.info("Saga handles registration otp confirmed event: {}", event);

            // Update saga state
            this.otpId = event.getOtpId();
            this.userId = GeneratorUtil.generateId(IdLength.USER_ID);

            // Associate saga with aggregate
            SagaLifecycle.associateWith(USER_ID, this.userId);

            // Send command to create user
            var tempUser = queryGateway.query(new GetTempUserByOtpIdQuery(this.otpId), TempUserEntity.class).join();
            var createUserCommand = CreateUserCommand.builder()
                    .userId(userId)
                    .username(tempUser.getUsername())
                    .email(tempUser.getEmail())
                    .fullName(tempUser.getFullName())
                    .password(tempUser.getPassword())
                    .salt(tempUser.getSalt())
                    .createdBy(tempUser.getUserId())
                    .build();
            commandGateway.send(createUserCommand);
        } catch (Exception e) {
            log.error("Error when handle registration otp confirmed event:", e);
            cancelOtpConfirmation();
        }
    }

    @SagaEventHandler(associationProperty = USER_ID)
    public void handle(UserCreationCompletedEvent event) {
        try {
            log.info("Saga handles user creation completed event: {}", event);
            var completedEvent = CompleteOtpConfirmationCommand.builder()
                    .otpId(this.otpId)
                    .build();
            commandGateway.send(completedEvent);
        } catch (Exception e) {
            log.error("Error when handle user creation completed event:", e);
            cancelOtpConfirmation();
        }
    }

    @SagaEventHandler(associationProperty = USER_ID)
    public void handle(UserCreationCanceledEvent event) {
        try {
            log.info("Saga handles user creation canceled event: {}", event);
            cancelOtpConfirmation();
        } catch (Exception e) {
            log.error("Error when handle user creation canceled event:", e);
            cancelOtpConfirmation();
        }
    }
}
