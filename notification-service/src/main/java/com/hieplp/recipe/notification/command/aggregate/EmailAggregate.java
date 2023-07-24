package com.hieplp.recipe.notification.command.aggregate;

import com.hieplp.recipe.common.command.commands.notification.email.CancelEmailCommand;
import com.hieplp.recipe.common.command.commands.notification.email.CompleteEmailCommand;
import com.hieplp.recipe.common.command.commands.notification.email.SendEmailCommand;
import com.hieplp.recipe.common.command.events.notification.email.EmailCanceledEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailSentEvent;
import com.hieplp.recipe.common.enums.notification.LogStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Map;
import java.util.UUID;

@Aggregate
@Slf4j
public class EmailAggregate {
    @AggregateIdentifier
    private final String id = UUID.randomUUID().toString();
    private String email;
    private String action;
    private Map<String, String> params;
    private String logId;
    private String createdBy;
    private Byte status;

    protected EmailAggregate() {
    }


    // -------------------------------------------------------------------------
    // XXX Send Email
    // -------------------------------------------------------------------------

    @CommandHandler
    public EmailAggregate(SendEmailCommand command) {
        log.info("Send email command: {}", command);

        // Validate
        if (command.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        if (command.getAction() == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }

        //
        var emailCreatedEvent = new EmailSentEvent();
        BeanUtils.copyProperties(command, emailCreatedEvent);
        //
        emailCreatedEvent.setLogId(UUID.randomUUID().toString());
        emailCreatedEvent.setStatus(LogStatus.INIT.getStatus());
        //
        AggregateLifecycle.apply(emailCreatedEvent);
    }

    @CommandHandler
    public EmailAggregate(CompleteEmailCommand command) {
        log.info("Complete email command: {}", command);
        //
        var emailCompletedEvent = new EmailCompletedEvent();
        BeanUtils.copyProperties(command, emailCompletedEvent);
        //
        emailCompletedEvent.setStatus(LogStatus.SUCCESS.getStatus());
        //
        AggregateLifecycle.apply(emailCompletedEvent);
    }

    // -------------------------------------------------------------------------
    // XXX Complete Email
    // -------------------------------------------------------------------------

    @CommandHandler
    public EmailAggregate(CancelEmailCommand command) {
        log.info("Cancel email command: {}", command);
        //
        var emailCanceledEvent = new EmailCanceledEvent();
        BeanUtils.copyProperties(command, emailCanceledEvent);
        //
        emailCanceledEvent.setStatus(LogStatus.FAILED.getStatus());
        //
        AggregateLifecycle.apply(emailCanceledEvent);
    }

    @EventSourcingHandler
    public void on(EmailSentEvent event) {
        this.email = event.getEmail();
        this.action = event.getAction();
        this.params = event.getParams();
        this.logId = event.getLogId();
        this.status = event.getStatus();
        this.createdBy = event.getCreatedBy();
    }

    // -------------------------------------------------------------------------
    // XXX Cancel Email
    // -------------------------------------------------------------------------

    @EventSourcingHandler
    public void on(EmailCompletedEvent event) {
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(EmailCanceledEvent event) {
        this.status = event.getStatus();
    }
}
