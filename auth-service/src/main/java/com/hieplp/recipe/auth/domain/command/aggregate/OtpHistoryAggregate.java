package com.hieplp.recipe.auth.domain.command.aggregate;

import com.hieplp.recipe.auth.domain.command.commands.history.create.CancelOtpHistoryCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.history.create.CompleteOtpHistoryCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreationCompletedEvent;
import com.hieplp.recipe.common.enums.otp.OtpHistoryStatus;
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

@Slf4j
@Aggregate
@Data
public class OtpHistoryAggregate {
    //
    @AggregateIdentifier
    private String otpHistoryId;
    private String otpId;
    private String otpCode;
    private byte status;
    private byte type;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;

    protected OtpHistoryAggregate() {
    }

    // -------------------------------------------------------------------------
    // XXX Create
    // -------------------------------------------------------------------------
    @CommandHandler
    public OtpHistoryAggregate(CreateOtpHistoryCommand command) {
        log.info("Handle create otp history command: {}", command);
        var createdEvent = new OtpHistoryCreatedEvent();
        BeanUtils.copyProperties(command, createdEvent);
        //
        createdEvent.setCreatedAt(DateUtil.now());
        //
        AggregateLifecycle.apply(createdEvent);
    }

    @CommandHandler
    public void handle(CompleteOtpHistoryCreationCommand command) {
        log.info("Handle complete otp history creation command: {}", command);
        log.info("Otp history id: {}", this);
        var completedEvent = new OtpHistoryCreationCompletedEvent();
        BeanUtils.copyProperties(command, completedEvent);
        //
        completedEvent.setOtpId(this.otpId);
        completedEvent.setModifiedAt(DateUtil.now());
        //
        AggregateLifecycle.apply(completedEvent);
    }

    @CommandHandler
    public void handle(CancelOtpHistoryCreationCommand command) {
        log.info("Handle cancel otp history creation command: {}", command);
        var canceledEvent = new OtpHistoryCreationCanceledEvent();
        BeanUtils.copyProperties(command, canceledEvent);
        //
        canceledEvent.setModifiedAt(DateUtil.now());
        canceledEvent.setStatus(OtpHistoryStatus.CANCELED.getStatus());
        //
        AggregateLifecycle.apply(canceledEvent);
    }

    @EventSourcingHandler
    public void on(OtpHistoryCreatedEvent event) {
        this.otpHistoryId = event.getOtpHistoryId();
        this.otpId = event.getOtpId();
        this.otpCode = event.getOtpCode();
        this.status = event.getStatus();
        this.type = event.getType();
        this.createdBy = event.getCreatedBy();
        this.createdAt = event.getCreatedAt();
        this.modifiedBy = event.getCreatedBy();
        this.modifiedAt = event.getCreatedAt();
    }

    @EventSourcingHandler
    public void on(OtpHistoryCreationCompletedEvent event) {
        this.modifiedAt = event.getModifiedAt();
    }

    @EventSourcingHandler
    public void on(OtpHistoryCreationCanceledEvent event) {
        this.status = event.getStatus();
        this.modifiedBy = event.getModifiedBy();
        this.modifiedAt = event.getModifiedAt();
    }
}
