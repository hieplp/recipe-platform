package com.hieplp.recipe.user.domain.command.eventhandler.password;

import com.hieplp.recipe.common.command.commands.password.update.CompletePasswordUpdateCommand;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdateCanceledEvent;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdateCompletedEvent;
import com.hieplp.recipe.common.command.events.password.update.PasswordUpdatedEvent;
import com.hieplp.recipe.user.common.repository.PasswordRepo;
import com.hieplp.recipe.user.common.repository.generate.tables.records.PasswordRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordUpdateEventHandler {

    private final PasswordRepo passwordRepo;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(PasswordUpdatedEvent event) {
        try {
            log.info("Handle password updated event: {}", event);

            var passwordRecord = new PasswordRecord()
                    .setUserid(event.getUserId())
                    .setPassword(event.getPassword())
                    .setSalt(event.getSalt());
            passwordRepo.updateNotNull(passwordRecord);

            // Complete the password update process
            var completeCommand = CompletePasswordUpdateCommand.builder()
                    .userId(event.getUserId())
                    .build();
            commandGateway.send(completeCommand);
        } catch (Exception e) {
            log.error("Error when handle password updated event:", e);
            var cancelCommand = CompletePasswordUpdateCommand.builder()
                    .userId(event.getUserId())
                    .build();
        }
    }

    @EventHandler
    private void handle(PasswordUpdateCompletedEvent event) {
        log.info("Handle password update completed event: {}", event);
    }

    @EventHandler
    private void handle(PasswordUpdateCanceledEvent event) {
        log.info("Handle password update canceled event: {}", event);
        // TODO: Do something to rollback the password update process
    }
}
