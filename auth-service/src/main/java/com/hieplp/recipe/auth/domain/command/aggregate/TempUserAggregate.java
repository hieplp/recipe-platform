package com.hieplp.recipe.auth.domain.command.aggregate;

import com.hieplp.recipe.auth.domain.command.commands.user.create.CompleteTempUserCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.user.create.CreateTempUserCommand;
import com.hieplp.recipe.auth.domain.command.event.user.TempUserCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.user.TempUserCreatedEvent;
import com.hieplp.recipe.common.enums.user.TempUserStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Slf4j
public class TempUserAggregate {
    @AggregateIdentifier
    private String userId;
    private String userName;
    private String fullName;
    private String email;
    private Byte status;
    private String createdBy;
    private String referenceId;
    private byte[] password;
    private byte[] salt;

    protected TempUserAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Create user
    // -------------------------------------------------------------------------
    @CommandHandler
    public TempUserAggregate(CreateTempUserCommand command) {
        log.info("Create temp user command: {}", command);
        var tempUserCreatedEvent = new TempUserCreatedEvent();
        BeanUtils.copyProperties(command, tempUserCreatedEvent);
        //
        tempUserCreatedEvent.setStatus(TempUserStatus.ACTIVE.getStatus());
        //
        AggregateLifecycle.apply(tempUserCreatedEvent);
    }

    @EventSourcingHandler
    public void on(TempUserCreatedEvent event) {
        this.userId = event.getUserId();
        this.userName = event.getUsername();
        this.fullName = event.getFullName();
        this.status = event.getStatus();
        this.createdBy = event.getCreatedBy();
        this.referenceId = event.getReferenceId();
    }

    // -------------------------------------------------------------------------
    // XXX Complete user
    // -------------------------------------------------------------------------

    @CommandHandler
    public void handle(CompleteTempUserCreationCommand command) {
        log.info("Complete temp user command: {}", command);
        var tempUserCompletedEvent = new TempUserCompletedEvent();
        BeanUtils.copyProperties(command, tempUserCompletedEvent);
        //
        tempUserCompletedEvent.setStatus(TempUserStatus.ACTIVE.getStatus());
        AggregateLifecycle.apply(tempUserCompletedEvent);
    }

    @CommandHandler
    public void on(TempUserCompletedEvent event) {
        this.userName = event.getUsername();
        this.fullName = event.getFullName();
        this.status = event.getStatus();
        this.createdBy = event.getCreatedBy();
        this.referenceId = event.getReferenceId();
    }
}
