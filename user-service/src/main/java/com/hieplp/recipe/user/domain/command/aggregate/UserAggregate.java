package com.hieplp.recipe.user.domain.command.aggregate;


import com.hieplp.recipe.user.domain.command.CreateUserCommand;
import com.hieplp.recipe.user.domain.command.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Slf4j
public class UserAggregate {
    @AggregateIdentifier
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
}

