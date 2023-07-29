package com.hieplp.recipe.user.domain.command.aggregate;


import com.hieplp.recipe.common.command.commands.user.create.CancelUserCreationCommand;
import com.hieplp.recipe.common.command.commands.user.create.CompleteUserCreationCommand;
import com.hieplp.recipe.common.command.commands.user.create.CreateUserCommand;
import com.hieplp.recipe.common.command.events.user.create.UserCreatedEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCanceledEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCompletedEvent;
import com.hieplp.recipe.common.enums.user.UserStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Aggregate
@Slf4j
@Data
public class UserAggregate {
    @AggregateIdentifier
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private Byte status;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
    private String referenceId;

    protected UserAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Create user
    // -------------------------------------------------------------------------
    @CommandHandler
    public UserAggregate(CreateUserCommand command) {
        log.info("Create user command: {}", command);

        var createdEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(command, createdEvent);

        createdEvent
                .setStatus(UserStatus.ACTIVE.getStatus())
                .setCreatedAt(LocalDateTime.now());

        AggregateLifecycle.apply(createdEvent);
    }

    @CommandHandler
    public void handle(CompleteUserCreationCommand command) {
        log.info("Complete user creation command: {}", command);
        var completedEvent = new UserCreationCompletedEvent();
        BeanUtils.copyProperties(command, completedEvent);
        completedEvent.setReferenceId(this.referenceId);
        AggregateLifecycle.apply(completedEvent);
    }

    @CommandHandler
    public void handle(CancelUserCreationCommand command) {
        log.info("Cancel user creation command: {}", command);
        var canceledEvent = new UserCreationCanceledEvent();
        BeanUtils.copyProperties(command, canceledEvent);
        canceledEvent.setReferenceId(this.referenceId);
        AggregateLifecycle.apply(canceledEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.userId = event.getUserId();
        this.username = event.getUsername();
        this.fullName = event.getFullName();
        this.email = event.getEmail();
        this.status = event.getStatus();
        this.createdBy = event.getCreatedBy();
        this.createdAt = event.getCreatedAt();
        this.modifiedBy = event.getCreatedBy();
        this.modifiedAt = event.getCreatedAt();
        this.referenceId = event.getReferenceId();
    }

    @EventSourcingHandler
    public void on(UserCreationCompletedEvent event) {
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(UserCreationCanceledEvent event) {
        this.status = event.getStatus();
    }
}

