package com.hieplp.recipe.auth.command.saga.register;

import com.hieplp.recipe.auth.command.event.register.RegisterOtpCreatedEvent;
import com.hieplp.recipe.common.command.commands.user.verify.VerifyUsernameCommand;
import com.hieplp.recipe.common.command.events.user.verify.EmailDuplicatedEvent;
import com.hieplp.recipe.common.command.events.user.verify.EmailVerifiedEvent;
import com.hieplp.recipe.common.command.events.user.verify.UsernameDuplicatedEvent;
import com.hieplp.recipe.common.command.events.user.verify.UsernameVerifiedEvent;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RegistrationOtpSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "otpId")
    public void handle(RegisterOtpCreatedEvent event) {
        log.info("RegisterOtpCreatedEvent in Saga for Otp Id : {}", event.getOtpId());

        final String userId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("userId", userId);

        //
        var verifyUsernameCommand = VerifyUsernameCommand.builder()
                .userId(userId)
                .username(event.getUsername())
                .build();
        commandGateway.sendAndWait(verifyUsernameCommand);

//        //
//        var verifyEmailCommand = VerifyEmailCommand.builder()
//                .userId(UUID.randomUUID().toString())
//                .email(event.getEmail())
//                .build();
//        commandGateway.sendAndWait(verifyEmailCommand);
    }

    // ---
    //
    // ---

    @SagaEventHandler(associationProperty = "userId")
    private void handle(UsernameVerifiedEvent event) {
        log.info("Username: {} is verified", event.getUsername());
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "userId")
    private void handle(UsernameDuplicatedEvent event) {
        log.info("Username: {} is duplicated", event.getUsername());
    }

    // ---
    //
    // ---

    @SagaEventHandler(associationProperty = "email")
    private void handle(EmailVerifiedEvent event) {
        log.info("Email: {} is verified", event.getEmail());
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "email")
    private void handle(EmailDuplicatedEvent event) {
        log.info("Email: {} is duplicated", event.getEmail());
    }

}
