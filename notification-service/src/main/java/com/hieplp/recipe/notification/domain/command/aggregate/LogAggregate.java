package com.hieplp.recipe.notification.domain.command.aggregate;

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

@Aggregate
@Slf4j
public class LogAggregate {
    @AggregateIdentifier
    private String logId;
    private String sendTo;
    private String action;
    private Map<String, String> params;
    private String createdBy;
    private Byte status;
    private String referenceId;

    protected LogAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Send Email
    // -------------------------------------------------------------------------

    @CommandHandler
    public LogAggregate(SendEmailCommand command) {
        log.info("Send email command: {}", command);
        //
        var emailCreatedEvent = new EmailSentEvent();
        BeanUtils.copyProperties(command, emailCreatedEvent);
        emailCreatedEvent.setStatus(LogStatus.INIT.getStatus());
        //
        AggregateLifecycle.apply(emailCreatedEvent);
    }

    @EventSourcingHandler
    public void on(EmailSentEvent event) {
        log.info("Email created event: {}", event);
        this.logId = event.getLogId();
        this.sendTo = event.getSendTo();
        this.action = event.getAction();
        this.params = event.getParams();
        this.status = event.getStatus();
        this.createdBy = event.getCreatedBy();
        this.referenceId = event.getReferenceId();
    }

    // -------------------------------------------------------------------------
    // XXX Complete Email
    // -------------------------------------------------------------------------

    @CommandHandler
    public void handle(CompleteEmailCommand command) {
        log.info("Complete email command: {}", command);
        //
        var emailCompletedEvent = new EmailCompletedEvent();
        BeanUtils.copyProperties(command, emailCompletedEvent);
        //
        emailCompletedEvent.setCreatedBy(this.createdBy);
        emailCompletedEvent.setStatus(LogStatus.SUCCESS.getStatus());
        //
        AggregateLifecycle.apply(emailCompletedEvent);
    }

    @EventSourcingHandler
    public void on(EmailCompletedEvent event) {
        log.warn("Email completed event: {} ------- {}", event, this.logId);
        this.status = event.getStatus();
    }

    // -------------------------------------------------------------------------
    // XXX Cancel Email
    // -------------------------------------------------------------------------

    @CommandHandler
    public void handle(CancelEmailCommand command) {
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
    public void on(EmailCanceledEvent event) {
        log.info("Email canceled event: {} ---- {}", event, this.logId);
        this.status = event.getStatus();
    }
}
