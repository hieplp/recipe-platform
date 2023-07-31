package com.hieplp.recipe.auth.domain.command.aggregate;

import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.CancelOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.CompleteOtpConfirmationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.ConfirmForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.confirm.ConfirmRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CancelOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CompleteOtpCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CreateForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.create.CreateRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.CancelOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.CompleteOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.ResendForgotOtpCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.ResendRegisterOtpCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.ForgotOtpConfirmedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.OtpConfirmationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.OtpConfirmationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.confirm.RegisterOtpConfirmedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.ForgotOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.OtpCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.create.RegisterOtpCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.ForgotOtpResentEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResendCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResendCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.RegisterOtpResentEvent;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
import com.hieplp.recipe.common.enums.otp.OtpType;
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

@Data
@Slf4j
@Aggregate
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

    // -------------------------------------------------------------------------
    // XXX Forgot - Create
    // -------------------------------------------------------------------------
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

    @EventSourcingHandler
    private void on(RegisterOtpCreatedEvent event) {
        BeanUtils.copyProperties(event, this);
        this.modifiedBy = event.getCreatedBy();
        this.modifiedAt = event.getCreatedAt();
    }

    @EventSourcingHandler
    private void on(ForgotOtpCreatedEvent event) {
        log.info("Forgot otp created event: {}", event.getOtpId());
        BeanUtils.copyProperties(event, this);
        this.modifiedBy = event.getCreatedBy();
        this.modifiedAt = event.getCreatedAt();
        log.info("Forgot otp created event: {}", this);
    }

    // -------------------------------------------------------------------------
    // XXX Creation - Complete
    // -------------------------------------------------------------------------
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


    @EventSourcingHandler
    private void on(OtpCreationCompletedEvent event) {
        this.status = event.getStatus();
        this.modifiedAt = event.getModifiedAt();
    }

    // -------------------------------------------------------------------------
    // XXX Creation - Cancel
    // -------------------------------------------------------------------------

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
    private void on(OtpCreationCanceledEvent event) {
        this.status = event.getStatus();
        this.modifiedAt = event.getModifiedAt();
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

    @EventSourcingHandler
    private void on(RegisterOtpResentEvent event) {
    }

    // -------------------------------------------------------------------------
    // XXX Resend - Forgot
    // -------------------------------------------------------------------------
    @CommandHandler
    private void handle(ResendForgotOtpCommand command) {
        log.info("Resend forgot otp command: {}", command);

        var otpResentEvent = new ForgotOtpResentEvent();
        BeanUtils.copyProperties(command, otpResentEvent);

        AggregateLifecycle.apply(otpResentEvent);
    }

    @CommandHandler
    private void on(ForgotOtpResentEvent event) {
    }

    // -------------------------------------------------------------------------
    // XXX Resend - Complete
    // -------------------------------------------------------------------------

    @CommandHandler
    private void handle(CompleteOtpResendCommand command) {
        log.info("Complete otp resend command: {}", command);
        var otpCompletedEvent = new OtpResendCompletedEvent();
        BeanUtils.copyProperties(command, otpCompletedEvent);
        //
        AggregateLifecycle.apply(otpCompletedEvent);
    }

    @EventSourcingHandler
    private void on(OtpResendCompletedEvent event) {
    }

    // -------------------------------------------------------------------------
    // XXX Resend - Cancel
    // -------------------------------------------------------------------------

    @CommandHandler
    private void handle(CancelOtpResendCommand command) {
        log.info("Cancel otp resend command: {}", command);
        var otpCanceledEvent = new OtpResendCanceledEvent();
        BeanUtils.copyProperties(command, otpCanceledEvent);
        //
        AggregateLifecycle.apply(otpCanceledEvent);
    }

    @EventSourcingHandler
    private void on(OtpResendCanceledEvent event) {
    }

    // -------------------------------------------------------------------------
    // XXX Confirmation - Register
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

    @EventSourcingHandler
    private void on(RegisterOtpConfirmedEvent event) {
        this.otpId = event.getOtpId();
        this.status = event.getStatus();
    }

    // -------------------------------------------------------------------------
    // XXX Confirmation - Forgot
    // -------------------------------------------------------------------------

    @CommandHandler
    private void handle(ConfirmForgotOtpCommand command) {
        log.info("Confirm forgot otp command: {}", command);
        var confirmedEvent = new ForgotOtpConfirmedEvent();
        BeanUtils.copyProperties(command, confirmedEvent);
        //
        confirmedEvent.setStatus(OtpStatus.CONFIRMED.getStatus());
        //
        AggregateLifecycle.apply(confirmedEvent);
    }

    // -------------------------------------------------------------------------
    // XXX Confirmation - Complete
    // -------------------------------------------------------------------------

    @CommandHandler
    private void handle(CompleteOtpConfirmationCommand command) {
        log.info("Complete registration otp confirmation command: {}", command);
        var completedEvent = new OtpConfirmationCompletedEvent();
        BeanUtils.copyProperties(command, completedEvent);
        //
        AggregateLifecycle.apply(completedEvent);
    }

    @EventSourcingHandler
    private void on(OtpConfirmationCompletedEvent event) {
        this.status = event.getStatus();
    }

    // -------------------------------------------------------------------------
    // XXX Confirmation - Cancel
    // -------------------------------------------------------------------------
    @CommandHandler
    private void handle(CancelOtpConfirmationCommand command) {
        log.info("Cancel registration otp confirmation command: {}", command);
        var canceledEvent = new OtpConfirmationCanceledEvent();
        BeanUtils.copyProperties(command, canceledEvent);
        //
        AggregateLifecycle.apply(canceledEvent);
    }

    @EventSourcingHandler
    private void on(OtpConfirmationCanceledEvent event) {
        this.status = event.getStatus();
    }

}