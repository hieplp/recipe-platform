package com.hieplp.recipe.notification.domain.command.eventhandler.email;

import com.hieplp.recipe.common.command.commands.notification.email.CancelEmailCommand;
import com.hieplp.recipe.common.command.commands.notification.email.CompleteEmailCommand;
import com.hieplp.recipe.common.command.events.notification.email.EmailCanceledEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailSentEvent;
import com.hieplp.recipe.common.enums.notification.SendVia;
import com.hieplp.recipe.notification.common.entity.TemplateEntity;
import com.hieplp.recipe.notification.common.repository.LogRepo;
import com.hieplp.recipe.notification.common.repository.generate.tables.records.LogRecord;
import com.hieplp.recipe.notification.common.util.EmailUtil;
import com.hieplp.recipe.notification.common.util.TemplateUtil;
import com.hieplp.recipe.notification.domain.query.queries.template.GetTemplateByActionAndSendViaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailEventHandler {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final LogRepo logRepo;
    private final JavaMailSender javaMailSender;

    @EventHandler
    private void handle(EmailSentEvent event) {
        try {
            log.info("Handle email created event: {}", event);

            //
            var getTemplateQuery = new GetTemplateByActionAndSendViaQuery(event.getAction(), SendVia.EMAIL.getSendVia());
            var template = Optional.ofNullable(queryGateway.query(getTemplateQuery, TemplateEntity.class).join());

            if (template.isEmpty()) {
                throw new IllegalArgumentException("Template not found");
            }

            // Build template params
            log.info("Template: {}", template);
            var builtTemplate = TemplateUtil.buildTemplate(template.get(), event.getParams());
            log.info("Built template: {}", builtTemplate);

            // Create log record
            var logRecord = new LogRecord()
                    .setLogid(event.getLogId())
                    .setAction(event.getAction())
                    .setSendvia(SendVia.EMAIL.getSendVia())
                    .setSendto(event.getSendTo())
                    .setSubject(builtTemplate.getSubject())
                    .setContent(builtTemplate.getContent())
                    .setStatus(event.getStatus())
                    .setCreatedby(event.getCreatedBy())
                    .setCreatedat(LocalDateTime.now())
                    .setModifiedby(event.getCreatedBy())
                    .setModifiedat(LocalDateTime.now());
            logRepo.save(logRecord);

            // Send email
            EmailUtil.sendMime(javaMailSender, event.getSendTo(), builtTemplate.getSubject(), builtTemplate.getContent());

            // Send email success
            var completeEmailCommand = CompleteEmailCommand.builder()
                    .logId(event.getLogId())
                    .build();
            commandGateway.sendAndWait(completeEmailCommand);
        } catch (Exception e) {
            log.error("Handle email created event error:", e);
            cancelEmail(event.getLogId(), event.getCreatedBy());
        }
    }

    @EventHandler
    private void handle(EmailCompletedEvent event) {
        try {
            log.info("Handle email sent event: {}", event);
            var logRecord = new LogRecord()
                    .setLogid(event.getLogId())
                    .setStatus(event.getStatus())
                    .setModifiedby(event.getCreatedBy())
                    .setModifiedat(LocalDateTime.now());
            logRepo.updateNotNull(logRecord);
        } catch (Exception e) {
            log.error("Handle email sent event error:", e);
            cancelEmail(event.getLogId(), event.getCreatedBy());
        }
    }

    @EventHandler
    private void handle(EmailCanceledEvent event) {
        try {
            log.info("Handle email failed event: {}", event);
            var logRecord = new LogRecord()
                    .setLogid(event.getLogId())
                    .setStatus(event.getStatus())
                    .setModifiedat(LocalDateTime.now())
                    .setModifiedat(LocalDateTime.now());
            logRepo.updateNotNull(logRecord);
        } catch (Exception e) {
            // Ignore
            log.error("Handle email failed event error:", e);
        }
    }

    private void cancelEmail(String logId, String createdBy) {
        var cancelEmailCommand = CancelEmailCommand.builder()
                .logId(logId)
                .createdBy(createdBy)
                .build();
        commandGateway.send(cancelEmailCommand);
    }
}
