package com.hieplp.recipe.user.domain.command.aggregate;


import com.hieplp.recipe.common.command.commands.password.update.CancelPasswordUpdateCommand;
import com.hieplp.recipe.common.command.commands.password.update.CompletePasswordUpdateCommand;
import com.hieplp.recipe.common.command.commands.password.update.UpdatePasswordCommand;
import com.hieplp.recipe.common.command.commands.user.create.CancelUserCreationCommand;
import com.hieplp.recipe.common.command.commands.user.create.CompleteUserCreationCommand;
import com.hieplp.recipe.common.command.commands.user.create.CreateUserCommand;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdateCanceledEvent;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdateCompletedEvent;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdatedEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreatedEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCanceledEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCompletedEvent;
import com.hieplp.recipe.common.enums.user.UserStatus;
import com.hieplp.recipe.common.util.DateUtil;
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
                .setCreatedAt(DateUtil.now());

        AggregateLifecycle.apply(createdEvent);
    }

    @CommandHandler
    public void handle(CompleteUserCreationCommand command) {
        log.info("Complete user creation command: {}", command);
        var completedEvent = new UserCreationCompletedEvent();
        BeanUtils.copyProperties(command, completedEvent);
        AggregateLifecycle.apply(completedEvent);
    }

    @CommandHandler
    public void handle(CancelUserCreationCommand command) {
        log.info("Cancel user creation command: {}", command);
        var canceledEvent = new UserCreationCanceledEvent();
        BeanUtils.copyProperties(command, canceledEvent);
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
    }

    @EventSourcingHandler
    public void on(UserCreationCompletedEvent event) {
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(UserCreationCanceledEvent event) {
        this.status = event.getStatus();
    }

    // -------------------------------------------------------------------------
    // XXX Password - Update
    // -------------------------------------------------------------------------

    @CommandHandler
    private void handle(UpdatePasswordCommand command) {
        log.info("Update password command: {}", command);

        var updatedEvent = new PasswordUpdatedEvent();
        BeanUtils.copyProperties(command, updatedEvent);

        updatedEvent.setModifiedAt(DateUtil.now());

        AggregateLifecycle.apply(updatedEvent);
    }

    @EventSourcingHandler
    private void on(PasswordUpdatedEvent event) {
        this.modifiedAt = event.getModifiedAt();
    }

    // -------------------------------------------------------------------------
    // XXX Password - Update - Complete
    // -------------------------------------------------------------------------

    @CommandHandler
    private void handle(CompletePasswordUpdateCommand command) {
        log.info("Update password command: {}", command);

        var completedEvent = new PasswordUpdateCompletedEvent();
        BeanUtils.copyProperties(command, completedEvent);

        completedEvent.setModifiedAt(DateUtil.now());

        AggregateLifecycle.apply(completedEvent);
    }

    @EventSourcingHandler
    private void on(PasswordUpdateCompletedEvent event) {
        this.modifiedAt = event.getModifiedAt();
    }

    // -------------------------------------------------------------------------
    // XXX Password - Update - Cancel
    // -------------------------------------------------------------------------

    @CommandHandler
    private void handle(CancelPasswordUpdateCommand command) {
        log.info("Update password command: {}", command);

        var canceledEvent = new PasswordUpdateCanceledEvent();
        BeanUtils.copyProperties(command, canceledEvent);

        canceledEvent.setModifiedAt(DateUtil.now());

        AggregateLifecycle.apply(canceledEvent);
    }

    @EventSourcingHandler
    private void on(PasswordUpdateCanceledEvent event) {
        this.modifiedAt = event.getModifiedAt();
    }
}


