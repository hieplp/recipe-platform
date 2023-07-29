package com.hieplp.recipe.auth.domain.command.eventhandler.user;

import com.hieplp.recipe.auth.common.repository.TempUserRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.TempUserRecord;
import com.hieplp.recipe.auth.config.component.UserRSAEncryption;
import com.hieplp.recipe.auth.domain.command.commands.user.create.CompleteTempUserCreationCommand;
import com.hieplp.recipe.auth.domain.command.event.user.TempUserCreatedEvent;
import com.hieplp.recipe.common.util.EncryptUtil;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEventHandler {

    private final CommandGateway commandGateway;
    private final UserRSAEncryption userRSAEncryption;
    private final TempUserRepo tempUserRepo;

    @EventHandler
    private void handle(TempUserCreatedEvent event) {
        byte[] password = new byte[0];
        try {
            log.info("Handle temporary user created event: {}", event);

            // Get password and salt
            var salt = GeneratorUtil.generateSalt();
            password = EncryptUtil.generatePassword(event.getPassword(), userRSAEncryption.getPrivateKey(), salt);

            // Save temporary user to database
            var tempUser = new TempUserRecord()
                    .setUserid(event.getUserId())
                    .setOtpid(event.getReferenceId())
                    .setUsername(event.getUsername())
                    .setFullname(event.getFullName())
                    .setEmail(event.getEmail())
                    .setStatus(event.getStatus())
                    .setPassword(password)
                    .setSalt(salt)
                    .setCreatedby(event.getCreatedBy())
                    .setCreatedat(LocalDateTime.now())
                    .setModifiedby(event.getCreatedBy())
                    .setModifiedat(LocalDateTime.now());
            tempUserRepo.save(tempUser);

            // Complete command
            var completeCommand = CompleteTempUserCreationCommand.builder()
                    .userId(event.getUserId())
                    .username(event.getUsername())
                    .email(event.getEmail())
                    .fullName(event.getFullName())
                    .createdBy(event.getCreatedBy())
                    .referenceId(event.getReferenceId())
                    .build();
            commandGateway.sendAndWait(completeCommand);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error when handle temporary user created event: {}", e.getMessage());
        } finally {
            // Clear password in memory for security
            Arrays.fill(password, Byte.MIN_VALUE);
        }
    }
}
