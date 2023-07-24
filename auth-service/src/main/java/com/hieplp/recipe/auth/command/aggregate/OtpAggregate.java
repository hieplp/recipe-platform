package com.hieplp.recipe.auth.command.aggregate;

import com.hieplp.recipe.auth.command.commands.CancelRegistrationOtpCommand;
import com.hieplp.recipe.auth.command.commands.CompleteRegistrationOtpCommand;
import com.hieplp.recipe.auth.command.commands.CreateRegistrationOtpCommand;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCanceledEvent;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCompletedEvent;
import com.hieplp.recipe.auth.command.event.register.RegistrationOtpCreatedEvent;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
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
public class OtpAggregate {
    @AggregateIdentifier
    private final String id = UUID.randomUUID().toString();
    private String otpId;
    private Byte status;
    private String userId;

    protected OtpAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Create
    // -------------------------------------------------------------------------

    @CommandHandler
    public OtpAggregate(CreateRegistrationOtpCommand command) {
        log.info("Create registration otp command: {}", command);
        //
        var otpCreatedEvent = new RegistrationOtpCreatedEvent();
        BeanUtils.copyProperties(command, otpCreatedEvent);
        //
        otpCreatedEvent.setStatus(OtpStatus.CREATED.getStatus());
        //
        AggregateLifecycle.apply(otpCreatedEvent);
    }

    @EventSourcingHandler
    public void on(RegistrationOtpCreatedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
        this.userId = event.getUserId();
    }

    // -------------------------------------------------------------------------
    // XXX Complete
    // -------------------------------------------------------------------------
    @CommandHandler
    public OtpAggregate(CompleteRegistrationOtpCommand command) {
        log.info("Complete registration otp command: {}", command);
        var otpCompletedEvent = new RegistrationOtpCompletedEvent();
        BeanUtils.copyProperties(command, otpCompletedEvent);
        //
        otpCompletedEvent.setStatus(OtpStatus.ACTIVATED.getStatus());
        //
        AggregateLifecycle.apply(otpCompletedEvent);
    }

    @EventSourcingHandler
    public void on(RegistrationOtpCompletedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
        this.userId = event.getUserId();
    }

    // -------------------------------------------------------------------------
    // XXX Cancel
    // -------------------------------------------------------------------------
    @CommandHandler
    public OtpAggregate(CancelRegistrationOtpCommand command) {
        log.info("Cancel registration otp command: {}", command);
        var otpCanceledEvent = new RegistrationOtpCanceledEvent();
        BeanUtils.copyProperties(command, otpCanceledEvent);
        //
        otpCanceledEvent.setStatus(OtpStatus.CANCELED.getStatus());
        //
        AggregateLifecycle.apply(otpCanceledEvent);
    }

    @EventSourcingHandler
    public void on(RegistrationOtpCanceledEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
        this.userId = event.getUserId();
    }

    // -------------------------------------------------------------------------
    // XXX Private methods
    // -------------------------------------------------------------------------
}