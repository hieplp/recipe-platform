//package com.hieplp.recipe.user.command.aggregate;
//
//import com.hieplp.recipe.common.command.commands.user.verify.VerifyEmailCommand;
//import com.hieplp.recipe.common.command.events.user.verify.EmailDuplicatedEvent;
//import com.hieplp.recipe.common.command.events.user.verify.EmailVerifiedEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.axonframework.commandhandling.CommandHandler;
//import org.axonframework.eventsourcing.EventSourcingHandler;
//import org.axonframework.modelling.command.AggregateIdentifier;
//import org.axonframework.modelling.command.AggregateLifecycle;
//import org.axonframework.spring.stereotype.Aggregate;
//
//@Aggregate
//@Slf4j
//public class EmailAggregate {
//    @AggregateIdentifier
//    private String email;
//
//    protected EmailAggregate() {
//    }
//
//    @CommandHandler
//    public EmailAggregate(VerifyEmailCommand event) {
//        try {
//            log.info("VerifyEmailCommand: {}", event);
//            var emailVerifiedEvent = EmailVerifiedEvent.builder()
//                    .email(event.getEmail())
//                    .build();
//            AggregateLifecycle.apply(emailVerifiedEvent);
//        } catch (Exception e) {
//            var emailDuplicatedEvent = EmailDuplicatedEvent.builder()
//                    .email(event.getEmail())
//                    .build();
//            AggregateLifecycle.apply(emailDuplicatedEvent);
//        }
//    }
//
//    @EventSourcingHandler
//    public void on(EmailVerifiedEvent event) {
//        this.email = event.getEmail();
//    }
//
//    @EventSourcingHandler
//    public void on(EmailDuplicatedEvent event) {
//        this.email = event.getEmail();
//    }
//}
