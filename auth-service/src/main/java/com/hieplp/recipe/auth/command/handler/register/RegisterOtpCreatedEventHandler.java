//package com.hieplp.recipe.auth.command.handler.register;
//
//import com.hieplp.recipe.auth.command.event.register.RegisterOtpCreatedEvent;
//import com.hieplp.recipe.common.command.commands.user.verify.VerifyUsernameCommand;
//import com.hieplp.recipe.common.command.events.user.verify.EmailDuplicatedEvent;
//import com.hieplp.recipe.common.command.events.user.verify.EmailVerifiedEvent;
//import com.hieplp.recipe.common.command.events.user.verify.UsernameDuplicatedEvent;
//import com.hieplp.recipe.common.command.events.user.verify.UsernameVerifiedEvent;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.axonframework.config.ProcessingGroup;
//import org.axonframework.eventhandling.EventHandler;
//import org.axonframework.modelling.saga.SagaEventHandler;
//import org.axonframework.spring.stereotype.Saga;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@RequiredArgsConstructor
//@Slf4j
//public class RegisterOtpCreatedEventHandler {
//
//    private final CommandGateway commandGateway;
//
//    @EventHandler
//    private void handle(RegisterOtpCreatedEvent event) {
//        log.info("Event: {}", event);
//
//        //
//        var verifyUsernameCommand = VerifyUsernameCommand.builder()
//                .userId(UUID.randomUUID().toString())
//                .username(event.getUsername())
//                .build();
//        commandGateway.sendAndWait(verifyUsernameCommand);
//
//        //
////        var verifyEmailCommand = VerifyEmailCommand.builder()
////                .email(event.getEmail())
////                .build();
////        commandGateway.sendAndWait(verifyEmailCommand);
//    }
//
//    @EventHandler
//    private void handle(UsernameVerifiedEvent event) {
//        log.info("Username: {} is verified", event.getUsername());
//    }
//
//    @EventHandler
//    private void handle(UsernameDuplicatedEvent event) {
//        log.info("Username: {} is duplicated", event.getUsername());
//    }
//
//    // ---
//    //
//    // ---
//
//    @EventHandler
//    private void handle(EmailVerifiedEvent event) {
//        log.info("Email: {} is verified", event.getEmail());
//    }
//
//    @EventHandler
//    private void handle(EmailDuplicatedEvent event) {
//        log.info("Email: {} is duplicated", event.getEmail());
//    }
//}
