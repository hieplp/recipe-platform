package com.hieplp.recipe.user.command.aggregate;


import com.hieplp.recipe.common.command.commands.user.verify.VerifyEmailCommand;
import com.hieplp.recipe.common.command.commands.user.verify.VerifyUsernameCommand;
import com.hieplp.recipe.common.command.events.user.verify.EmailDuplicatedEvent;
import com.hieplp.recipe.common.command.events.user.verify.EmailVerifiedEvent;
import com.hieplp.recipe.common.command.events.user.verify.UsernameDuplicatedEvent;
import com.hieplp.recipe.common.command.events.user.verify.UsernameVerifiedEvent;
import com.hieplp.recipe.user.command.CreateUserCommand;
import com.hieplp.recipe.user.command.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Aggregate
@Slf4j
public class UserAggregate {
    @AggregateIdentifier
    private final String id = UUID.randomUUID().toString();
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private Byte status;
    private String createdBy;
    private Long createdAt;
    private String modifiedBy;
    private Long modifiedAt;

    protected UserAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Create user
    // -------------------------------------------------------------------------
    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        log.info("Create user command: {}", command);
        // Validate command

        // Create event
        var userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(command, userCreatedEvent);
        AggregateLifecycle.apply(userCreatedEvent);
    }

    // -------------------------------------------------------------------------
    // XXX Verify username
    // -------------------------------------------------------------------------
    @CommandHandler
    public UserAggregate(VerifyUsernameCommand command) {
        try {
            log.info("Verify username command: {}", command);
            var usernameVerifiedEvent = UsernameVerifiedEvent.builder()
                    .userId(command.getUserId())
                    .username(command.getUsername())
                    .build();
            var t = 0;
            System.out.printf(String.valueOf(0 / t));
            AggregateLifecycle.apply(usernameVerifiedEvent);
        } catch (Exception e) {
            var usernameDuplicatedEvent = UsernameDuplicatedEvent.builder()
                    .userId(command.getUserId())
                    .username(command.getUsername())
                    .build();
            AggregateLifecycle.apply(usernameDuplicatedEvent);
        }
    }

    // -------------------------------------------------------------------------
    // XXX Verify email
    // -------------------------------------------------------------------------
    @CommandHandler
    public UserAggregate(VerifyEmailCommand event) {
        try {
            log.info("VerifyEmailCommand: {}", event);
            var emailVerifiedEvent = EmailVerifiedEvent.builder()
                    .userId(event.getUserId())
                    .email(event.getEmail())
                    .build();
            AggregateLifecycle.apply(emailVerifiedEvent);
        } catch (Exception e) {
            var emailDuplicatedEvent = EmailDuplicatedEvent.builder()
                    .userId(event.getUserId())
                    .email(event.getEmail())
                    .build();
            AggregateLifecycle.apply(emailDuplicatedEvent);
        }
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.userId = event.getUserId();
        this.username = event.getUsername();
        this.fullName = event.getFullName();
        this.status = event.getStatus();
        this.createdBy = event.getCreatedBy();
        this.createdAt = event.getCreatedAt();
        this.modifiedBy = event.getModifiedBy();
        this.modifiedAt = event.getModifiedAt();
    }

    @EventSourcingHandler
    public void on(UsernameVerifiedEvent event) {
        log.info("Username: {} is verified", event.getUsername());
        this.userId = event.getUserId();
        this.username = event.getUsername();
    }

    @EventSourcingHandler
    public void on(UsernameDuplicatedEvent event) {
        log.info("Username: {} is duplicated", event.getUsername());
        this.userId = event.getUserId();
        this.username = event.getUsername();
    }

    @EventSourcingHandler
    public void on(EmailVerifiedEvent event) {
        log.info("Email: {} is verified", event.getEmail());
        this.userId = event.getUserId();
        this.email = event.getEmail();
    }

    @EventSourcingHandler
    public void on(EmailDuplicatedEvent event) {
        log.info("Email: {} is duplicated", event.getEmail());
        this.userId = event.getUserId();
        this.email = event.getEmail();
    }
}

