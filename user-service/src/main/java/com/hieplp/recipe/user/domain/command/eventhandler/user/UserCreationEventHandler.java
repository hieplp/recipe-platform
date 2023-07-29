package com.hieplp.recipe.user.domain.command.eventhandler.user;

import com.hieplp.recipe.common.command.commands.user.create.CancelUserCreationCommand;
import com.hieplp.recipe.common.command.commands.user.create.CompleteUserCreationCommand;
import com.hieplp.recipe.common.command.events.user.create.UserCreatedEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCanceledEvent;
import com.hieplp.recipe.common.command.events.user.create.UserCreationCompletedEvent;
import com.hieplp.recipe.user.common.repository.PasswordRepo;
import com.hieplp.recipe.user.common.repository.UserRepo;
import com.hieplp.recipe.user.common.repository.generate.tables.records.PasswordRecord;
import com.hieplp.recipe.user.common.repository.generate.tables.records.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreationEventHandler {

    private final UserRepo userRepo;
    private final PasswordRepo passwordRepo;
    private final CommandGateway commandGateway;

    @EventHandler
    private void handle(UserCreatedEvent event) {
        try {
            log.info("Handle user created event: {}", event);
            var userRecord = new UserRecord()
                    .setUserid(event.getUserId())
                    .setUsername(event.getUsername())
                    .setEmail(event.getEmail())
                    .setFullname(event.getFullName())
                    .setStatus(event.getStatus())
                    .setCreatedby(event.getUserId())
                    .setCreatedat(event.getCreatedAt())
                    .setModifiedby(event.getUserId())
                    .setModifiedat(event.getCreatedAt());
            userRepo.save(userRecord);

            var passwordRecord = new PasswordRecord()
                    .setUserid(event.getUserId())
                    .setPassword(event.getPassword())
                    .setSalt(event.getSalt())
                    .setCreatedby(event.getUserId())
                    .setCreatedat(LocalDateTime.now())
                    .setModifiedby(event.getUserId())
                    .setModifiedat(LocalDateTime.now());
            passwordRepo.save(passwordRecord);

            completeUserCreation(event.getUserId());
        } catch (Exception e) {
            log.error("Error while saving user", e);
            cancelUserCreation(event.getUserId());
        }
    }

    @EventHandler
    private void handle(UserCreationCompletedEvent event) {
        log.info("Handle user creation completed event: {}", event);
    }

    @EventHandler
    private void handle(UserCreationCanceledEvent event) {
        log.info("Handle user creation canceled event: {}", event);
        var userRecord = new UserRecord()
                .setUserid(event.getUserId());
        userRepo.delete(userRecord);

        var passwordRecord = new PasswordRecord()
                .setUserid(event.getUserId());
        passwordRepo.delete(passwordRecord);
    }

    private void completeUserCreation(String userId) {
        log.info("Complete user creation: {}", userId);
        var completeUserCreationCommand = CompleteUserCreationCommand.builder()
                .userId(userId)
                .build();
        commandGateway.sendAndWait(completeUserCreationCommand);
    }

    private void cancelUserCreation(String userId) {
        log.info("Cancel user creation: {}", userId);
        var cancelUserCreationCommand = CancelUserCreationCommand.builder()
                .userId(userId)
                .build();
        commandGateway.sendAndWait(cancelUserCreationCommand);
    }
}
