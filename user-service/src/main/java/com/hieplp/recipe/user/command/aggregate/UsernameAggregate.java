//package com.hieplp.recipe.user.command.aggregate;
//
//import com.hieplp.recipe.common.command.commands.user.verify.VerifyUsernameCommand;
//import com.hieplp.recipe.common.command.events.user.verify.UsernameDuplicatedEvent;
//import com.hieplp.recipe.common.command.events.user.verify.UsernameVerifiedEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.axonframework.commandhandling.CommandHandler;
//import org.axonframework.eventsourcing.EventSourcingHandler;
//import org.axonframework.modelling.command.AggregateIdentifier;
//import org.axonframework.modelling.command.AggregateLifecycle;
//import org.axonframework.spring.stereotype.Aggregate;
//
//import java.util.UUID;
//
//@Aggregate
//@Slf4j
//public class UsernameAggregate {
//    @AggregateIdentifier
//    private final String id = UUID.randomUUID().toString();
//    private String username;
//
//    public UsernameAggregate() {
//    }
//
//    @CommandHandler
//    public UsernameAggregate(VerifyUsernameCommand command) {
//        try {
//            log.info("Verify username command: {}", command);
//            var usernameVerifiedEvent = UsernameVerifiedEvent.builder()
//                    .username(command.getUsername())
//                    .build();
//            AggregateLifecycle.apply(usernameVerifiedEvent);
//        } catch (Exception e) {
//            var usernameDuplicatedEvent = UsernameDuplicatedEvent.builder()
//                    .username(command.getUsername())
//                    .build();
//            AggregateLifecycle.apply(usernameDuplicatedEvent);
//        }
//    }
//
//    @EventSourcingHandler
//    public void on(UsernameVerifiedEvent event) {
//        log.info("Username: {} is verified", event.getUsername());
//        this.username = event.getUsername();
//    }
//
//    @EventSourcingHandler
//    public void on(UsernameDuplicatedEvent event) {
//        log.info("Username: {} is duplicated", event.getUsername());
//        this.username = event.getUsername();
//    }
//}
