package com.hieplp.recipe.auth.domain.command.aggregate;

import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.CancelRegisterOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.CompleteRegisterOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CancelRegisterOtpCreateCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CompleteRegisterOtpCreateCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.CancelRegisterOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.CompleteRegisterOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.ResendRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.create.RegisterOtpCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.create.RegisterOtpCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.create.RegisterOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResentEvent;
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

@Aggregate
@Slf4j
public class OtpAggregate {
    @AggregateIdentifier
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

    @CommandHandler
    public void handle(CompleteRegisterOtpCreateCommand command) {
        log.info("Complete registration otp command: {}", command);
        var otpCompletedEvent = new RegisterOtpCompletedEvent();
        BeanUtils.copyProperties(command, otpCompletedEvent);
        //
        otpCompletedEvent.setStatus(OtpStatus.ACTIVATED.getStatus());
        //
        AggregateLifecycle.apply(otpCompletedEvent);
    }

    @CommandHandler
    public void handle(CancelRegisterOtpCreateCommand command) {
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
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(RegisterOtpCanceledEvent event) {
        this.status = event.getStatus();
    }

    // -------------------------------------------------------------------------
    // XXX Confirm
    // -------------------------------------------------------------------------

    @CommandHandler
    public void handle(ConfirmRegisterOtpCommand command) {
        log.info("Confirm registration otp command: {}", command);
        var confirmedEvent = new RegisterOtpConfirmedEvent();
        BeanUtils.copyProperties(command, confirmedEvent);
        //
        confirmedEvent.setStatus(OtpStatus.CONFIRMED.getStatus());
        //
        AggregateLifecycle.apply(confirmedEvent);
    }

    @CommandHandler
    public void handle(CompleteRegisterOtpConfirmationCommand command) {
        log.info("Complete registration otp confirmation command: {}", command);
        var completedEvent = new RegisterOtpConfirmationCompletedEvent();
        BeanUtils.copyProperties(command, completedEvent);
        //
        AggregateLifecycle.apply(completedEvent);
    }

    @CommandHandler
    public void handle(CancelRegisterOtpConfirmationCommand command) {
        log.info("Cancel registration otp confirmation command: {}", command);
        var canceledEvent = new RegisterOtpConfirmationCanceledEvent();
        BeanUtils.copyProperties(command, canceledEvent);
        //
        AggregateLifecycle.apply(canceledEvent);
    }

    @EventSourcingHandler
    public void on(RegisterOtpConfirmedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(RegisterOtpConfirmationCompletedEvent event) {
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(RegisterOtpConfirmationCanceledEvent event) {
        this.status = event.getStatus();
    }

    // -------------------------------------------------------------------------
    // XXX Register - Resend
    // -------------------------------------------------------------------------
    @CommandHandler
    private void handle(ResendRegisterOtpCommand command) {
        log.info("Resend registration otp command: {}", command);
        var otpCreatedEvent = new RegisterOtpResentEvent();
        BeanUtils.copyProperties(command, otpCreatedEvent);
        //
        AggregateLifecycle.apply(otpCreatedEvent);
    }

    @CommandHandler
    private void handle(CompleteRegisterOtpResendCommand command) {
        log.info("Complete registration otp resend command: {}", command);
        var otpCompletedEvent = new RegisterOtpResendCompletedEvent();
        BeanUtils.copyProperties(command, otpCompletedEvent);
        //
        AggregateLifecycle.apply(otpCompletedEvent);
    }

    @CommandHandler
    private void handle(CancelRegisterOtpResendCommand command) {
        log.info("Cancel registration otp resend command: {}", command);
        var otpCanceledEvent = new RegisterOtpResendCanceledEvent();
        BeanUtils.copyProperties(command, otpCanceledEvent);
        //
        AggregateLifecycle.apply(otpCanceledEvent);
    }

    @EventSourcingHandler
    private void on(RegisterOtpResentEvent event) {
    }

    @EventSourcingHandler
    private void on(RegisterOtpResendCompletedEvent event) {
    }

    @EventSourcingHandler
    private void on(RegisterOtpResendCanceledEvent event) {
    }

    // -------------------------------------------------------------------------
    // XXX Private methods
    // -------------------------------------------------------------------------
}