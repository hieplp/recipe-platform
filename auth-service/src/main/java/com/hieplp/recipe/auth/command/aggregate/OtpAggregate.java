package com.hieplp.recipe.auth.command.aggregate;

import com.hieplp.recipe.auth.command.commands.register.CancelRegisterOtpCommand;
import com.hieplp.recipe.auth.command.commands.register.CompleteRegisterOtpCommand;
import com.hieplp.recipe.auth.command.commands.register.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.command.commands.register.VerifyRegisterOtpCommand;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpCanceledEvent;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpCompletedEvent;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpCreatedEvent;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpVerifiedEvent;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
import com.hieplp.recipe.common.enums.otp.OtpType;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Aggregate
@Slf4j
public class OtpAggregate {
    @AggregateIdentifier
    private final String id = UUID.randomUUID().toString();
    private String otpId;
    private Byte status;
    private String userId;
    private String otpCode;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;

    protected OtpAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Create
    // -------------------------------------------------------------------------

    @CommandHandler
    public OtpAggregate(CreateRegisterOtpCommand command) {
        log.info("Create registration otp command: {}", command);
        //
        var otpCreatedEvent = new RegisterOtpCreatedEvent();
        BeanUtils.copyProperties(command, otpCreatedEvent);
        //
        otpCreatedEvent.setType(OtpType.REGISTER.getType());
        otpCreatedEvent.setStatus(OtpStatus.CREATED.getStatus());
        //
        AggregateLifecycle.apply(otpCreatedEvent);
    }

    // -------------------------------------------------------------------------
    // XXX Complete
    // -------------------------------------------------------------------------
    @CommandHandler
    public OtpAggregate(CompleteRegisterOtpCommand command) {
        log.info("Complete registration otp command: {}", command);
        var otpCompletedEvent = new RegisterOtpCompletedEvent();
        BeanUtils.copyProperties(command, otpCompletedEvent);
        //
        otpCompletedEvent.setStatus(OtpStatus.ACTIVATED.getStatus());
        //
        AggregateLifecycle.apply(otpCompletedEvent);
    }

    // -------------------------------------------------------------------------
    // XXX Cancel
    // -------------------------------------------------------------------------
    @CommandHandler
    public OtpAggregate(CancelRegisterOtpCommand command) {
        log.info("Cancel registration otp command: {}", command);
        var otpCanceledEvent = new RegisterOtpCanceledEvent();
        BeanUtils.copyProperties(command, otpCanceledEvent);
        //
        otpCanceledEvent.setStatus(OtpStatus.CANCELED.getStatus());
        //
        AggregateLifecycle.apply(otpCanceledEvent);
    }

    @EventSourcingHandler
    public void on(RegisterOtpCreatedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
        this.userId = event.getUserId();
        this.issuedAt = event.getIssuedAt();
        this.expiredAt = event.getExpiredAt();
    }

    @EventSourcingHandler
    public void on(RegisterOtpCompletedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(RegisterOtpCanceledEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
    }

    // -------------------------------------------------------------------------
    // XXX Verify
    // -------------------------------------------------------------------------

    @CommandHandler
    public OtpAggregate(VerifyRegisterOtpCommand command) {
        log.info("Verify registration otp command: {}", command);
        //
        var otpVerifiedEvent = new RegisterOtpVerifiedEvent();
        BeanUtils.copyProperties(command, otpVerifiedEvent);
        //
        AggregateLifecycle.apply(otpVerifiedEvent);
    }

    // -------------------------------------------------------------------------
    // XXX Private methods
    // -------------------------------------------------------------------------
}