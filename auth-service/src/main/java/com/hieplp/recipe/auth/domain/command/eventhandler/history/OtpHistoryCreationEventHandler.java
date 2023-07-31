package com.hieplp.recipe.auth.domain.command.eventhandler.history;

import com.hieplp.recipe.auth.common.repository.OtpHistoryRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.OtphistoryRecord;
import com.hieplp.recipe.auth.domain.command.commands.history.create.CancelOtpHistoryCreationCommand;
import com.hieplp.recipe.auth.domain.command.commands.history.create.CompleteOtpHistoryCreationCommand;
import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreatedEvent;
import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreationCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreationCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpHistoryCreationEventHandler {

    private final OtpHistoryRepo otpHistoryRepo;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(OtpHistoryCreatedEvent event) {
        try {
            log.info("Handle otp history created event: {}", event);
            var otpHistoryRecord = new OtphistoryRecord()
                    .setOtphistoryid(event.getOtpHistoryId())
                    .setOtpid(event.getOtpId())
                    .setOtpcode(event.getOtpCode())
                    .setStatus(event.getStatus())
                    .setType(event.getType())
                    .setCreatedby(event.getCreatedBy())
                    .setCreatedat(event.getCreatedAt())
                    .setModifiedby(event.getCreatedBy())
                    .setModifiedat(event.getCreatedAt());
            otpHistoryRepo.save(otpHistoryRecord);

            var completedCommand = CompleteOtpHistoryCreationCommand.builder()
                    .otpHistoryId(event.getOtpHistoryId())
                    .modifiedBy(event.getCreatedBy())
                    .build();
            commandGateway.send(completedCommand);
        } catch (Exception e) {
            log.error("Error when handle otp history created event: {}", event, e);
            var cancelCommand = CancelOtpHistoryCreationCommand.builder()
                    .otpHistoryId(event.getOtpHistoryId())
                    .modifiedBy(event.getCreatedBy())
                    .build();
            commandGateway.send(cancelCommand);
        }
    }

    @EventHandler
    private void handle(OtpHistoryCreationCompletedEvent event) {
        log.info("Handle otp history creation completed event: {}", event);
    }

    @EventHandler
    private void handle(OtpHistoryCreationCanceledEvent event) {
        log.info("Handle otp history creation canceled event: {}", event);
        var otpHistoryRecord = new OtphistoryRecord()
                .setOtpid(event.getOtpHistoryId())
                .setStatus(event.getStatus())
                .setModifiedby(event.getModifiedBy())
                .setModifiedat(event.getModifiedAt());
        otpHistoryRepo.updateNotNull(otpHistoryRecord);
    }
}
