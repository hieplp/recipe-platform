package com.hieplp.recipe.auth.domain.command.saga.otp;

import com.hieplp.recipe.auth.common.entity.TempUserEntity;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.CancelRegisterOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.CompleteRegisterOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmedEvent;
import com.hieplp.recipe.auth.domain.query.queries.tempuser.GetTempUserByOtpIdQuery;
import com.hieplp.recipe.common.command.commands.user.create.CreateUserCommand;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCanceledEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCompletedEvent;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class RegisterOtpConfirmationSaga {

    private static final String OTP_ID = "otpId";
    private static final String USER_ID = "userId";

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    public void handle(RegisterOtpConfirmedEvent event) {
        try {
            log.info("Saga handles registration otp confirmed event: {}", event);


            final String userId = GeneratorUtil.randomString(10);
            SagaLifecycle.associateWith(USER_ID, userId);

            var tempUser = queryGateway.query(new GetTempUserByOtpIdQuery(event.getOtpId()), TempUserEntity.class).join();
            var createUserCommand = CreateUserCommand.builder()
                    .userId(userId)
                    .username(tempUser.getUsername())
                    .email(tempUser.getEmail())
                    .fullName(tempUser.getFullName())
                    .password(tempUser.getPassword())
                    .salt(tempUser.getSalt())
                    .createdBy(tempUser.getUserId())
                    .referenceId(event.getOtpId())
                    .build();
            commandGateway.sendAndWait(createUserCommand);
        } catch (Exception e) {
            log.error("Error while sending create user command", e);
            e.printStackTrace();
            cancelRegisterOtpConfirmation(event.getOtpId(), event.getUserId());
        }
    }

    @SagaEventHandler(associationProperty = USER_ID)
    public void handle(UserCreationCompletedEvent event) {
        try {
            log.info("Saga handles user creation completed event: {}", event);
            var completedEvent = CompleteRegisterOtpConfirmationCommand.builder()
                    .otpId(event.getReferenceId())
                    .build();
            commandGateway.sendAndWait(completedEvent);
        } catch (Exception e) {
            log.error("Error while sending complete register otp confirmation command", e);
            cancelRegisterOtpConfirmation(event.getReferenceId(), event.getUserId());
        }
    }

    @SagaEventHandler(associationProperty = USER_ID)
    public void handle(UserCreationCanceledEvent event) {
        log.info("Saga handles user creation canceled event: {}", event);
        cancelRegisterOtpConfirmation(event.getReferenceId(), event.getUserId());
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    public void handle(RegisterOtpConfirmationCompletedEvent command) {
        log.info("Saga handles complete register otp confirmation command: {}", command);
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    public void handle(RegisterOtpConfirmationCanceledEvent event) {
        log.info("Saga handles cancel register otp confirmation command: {}", event);
        SagaLifecycle.end();
    }

    private void cancelRegisterOtpConfirmation(String otpId, String modifiedBy) {
        var cancelCommand = CancelRegisterOtpConfirmationCommand.builder()
                .otpId(otpId)
                .modifiedBy(modifiedBy)
                .build();
        commandGateway.sendAndWait(cancelCommand);
    }
}
