package com.hieplp.recipe.auth.domain.command.aggregate;

import com.hieplp.recipe.auth.domain.command.commands.otp.create.CancelOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CompleteOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.forgot.create.CreateForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.CancelRegisterOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.CompleteRegisterOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.CancelRegisterOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.CompleteRegisterOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.ResendRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.create.ForgotOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.RegisterOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.register.confirm.RegisterOtpConfirmedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResentEvent;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
import com.hieplp.recipe.common.enums.otp.OtpType;
import com.hieplp.recipe.common.util.DateUtil;
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
    private Byte type;
    private String sendTo;
    private String userId;
    private String otpCode;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;

    protected OtpAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Register - Create
    // -------------------------------------------------------------------------

    @CommandHandler
    public OtpAggregate(CreateRegisterOtpCommand command) {
        log.info("Create registration otp command: {}", command);

        var otpCreatedEvent = new RegisterOtpCreatedEvent();
        BeanUtils.copyProperties(command, otpCreatedEvent);

        otpCreatedEvent
                .setType(OtpType.REGISTER.getType())
                .setStatus(OtpStatus.CREATED.getStatus())
                .setCreatedBy(command.getUserId())
                .setCreatedAt(DateUtil.now());

        AggregateLifecycle.apply(otpCreatedEvent);
    }

    @CommandHandler
    public OtpAggregate(CreateForgotOtpCommand command) {
        log.info("Create forgot otp command: {}", command);

        var otpCreatedEvent = new ForgotOtpCreatedEvent();
        BeanUtils.copyProperties(command, otpCreatedEvent);

        otpCreatedEvent
                .setType(OtpType.FORGOT_PASSWORD.getType())
                .setStatus(OtpStatus.CREATED.getStatus())
                .setCreatedAt(LocalDateTime.now());

        AggregateLifecycle.apply(otpCreatedEvent);
    }


    // -------------------------------------------------------------------------
    // XXX Register - Confirm
    // -------------------------------------------------------------------------


    @CommandHandler
    private void handle(ConfirmRegisterOtpCommand command) {
        log.info("Confirm registration otp command: {}", command);
        var confirmedEvent = new RegisterOtpConfirmedEvent();
        BeanUtils.copyProperties(command, confirmedEvent);
        //
        confirmedEvent.setStatus(OtpStatus.CONFIRMED.getStatus());
        //
        AggregateLifecycle.apply(confirmedEvent);
    }

    @CommandHandler
    private void handle(CompleteRegisterOtpConfirmationCommand command) {
        log.info("Complete registration otp confirmation command: {}", command);
        var completedEvent = new RegisterOtpConfirmationCompletedEvent();
        BeanUtils.copyProperties(command, completedEvent);
        //
        AggregateLifecycle.apply(completedEvent);
    }

    @CommandHandler
    private void handle(CancelRegisterOtpConfirmationCommand command) {
        log.info("Cancel registration otp confirmation command: {}", command);
        var canceledEvent = new RegisterOtpConfirmationCanceledEvent();
        BeanUtils.copyProperties(command, canceledEvent);
        //
        AggregateLifecycle.apply(canceledEvent);
    }

    @EventSourcingHandler
    private void on(RegisterOtpConfirmedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    private void on(RegisterOtpConfirmationCompletedEvent event) {
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    private void on(RegisterOtpConfirmationCanceledEvent event) {
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

    // -------------------------------------------------------------------------
    // XXX Forgot - Create
    // -------------------------------------------------------------------------

    @EventSourcingHandler
    private void on(RegisterOtpResendCanceledEvent event) {
    }

    @CommandHandler
    private void handle(CompleteOtpCreationCommand command) {
        log.info("Complete forgot otp command: {}", command);

        var otpCompletedEvent = new OtpCreationCompletedEvent();
        BeanUtils.copyProperties(command, otpCompletedEvent);

        otpCompletedEvent
                .setStatus(OtpStatus.ACTIVATED.getStatus())
                .setModifiedAt(LocalDateTime.now());

        AggregateLifecycle.apply(otpCompletedEvent);
    }

    @CommandHandler
    private void handle(CancelOtpCreationCommand command) {
        log.info("Cancel forgot otp command: {}", command);

        var otpCanceledEvent = new OtpCreationCanceledEvent();
        BeanUtils.copyProperties(command, otpCanceledEvent);

        otpCanceledEvent
                .setStatus(OtpStatus.CANCELED.getStatus())
                .setModifiedAt(LocalDateTime.now());

        AggregateLifecycle.apply(otpCanceledEvent);
    }

    @EventSourcingHandler
    private void on(ForgotOtpCreatedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
        this.type = event.getType();
        this.sendTo = event.getSendTo();
        this.issuedAt = event.getIssuedAt();
        this.expiredAt = event.getExpiredAt();
        this.createdBy = event.getCreatedBy();
        this.createdAt = event.getCreatedAt();
        this.modifiedBy = event.getCreatedBy();
        this.modifiedAt = event.getCreatedAt();
    }

    @EventSourcingHandler
    private void on(OtpCreationCompletedEvent event) {
        this.status = event.getStatus();
        this.modifiedAt = event.getModifiedAt();
    }

    @EventSourcingHandler
    private void on(OtpCreationCanceledEvent event) {
        this.status = event.getStatus();
        this.modifiedAt = event.getModifiedAt();
    }

    // -------------------------------------------------------------------------
    // XXX Private methods
    // -------------------------------------------------------------------------
}