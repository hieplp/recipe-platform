package com.hieplp.recipe.auth.domain.command.eventhandler.user;

import com.hieplp.recipe.auth.common.repository.TempUserRepo;
import com.hieplp.recipe.auth.common.repository.generate.tables.records.TempUserRecord;
import com.hieplp.recipe.auth.config.component.UserRSAEncryption;
import com.hieplp.recipe.auth.domain.command.commands.user.create.CompleteTempUserCreationCommand;
import com.hieplp.recipe.auth.domain.command.event.user.TempUserCreatedEvent;
import com.hieplp.recipe.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEventHandler {

    private final CommandGateway commandGateway;
    private final UserRSAEncryption userRSAEncryption;
    private final TempUserRepo tempUserRepo;

    @EventHandler
    private void handle(TempUserCreatedEvent event) {
        try {
            log.info("Handle temporary user created event: {}", event);

            var tempUser = new TempUserRecord()
                    .setUserid(event.getUserId())
                    .setOtpid(event.getReferenceId())
                    .setUsername(event.getUsername())
                    .setFullname(event.getFullName())
                    .setEmail(event.getEmail())
                    .setStatus(event.getStatus())
                    .setPassword(event.getPassword())
                    .setSalt(event.getSalt())
                    .setCreatedby(event.getCreatedBy())
                    .setCreatedat(DateUtil.now())
                    .setModifiedby(event.getCreatedBy())
                    .setModifiedat(DateUtil.now());
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
            log.error("Error when handle temporary user created event: {}", e.getMessage());
        }
    }
}
