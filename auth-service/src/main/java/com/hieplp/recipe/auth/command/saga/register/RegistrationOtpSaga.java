package com.hieplp.recipe.auth.command.saga.register;

import com.hieplp.recipe.auth.command.commands.register.CancelRegisterOtpCommand;
import com.hieplp.recipe.auth.command.commands.register.CompleteRegisterOtpCommand;
import com.hieplp.recipe.auth.command.commands.user.CreateTempUserCommand;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpCanceledEvent;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpCompletedEvent;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpCreatedEvent;
import com.hieplp.recipe.auth.command.event.user.TempUserCompletedEvent;
import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.query.queries.GetOtpQuery;
import com.hieplp.recipe.common.command.commands.notification.email.SendEmailCommand;
import com.hieplp.recipe.common.command.events.notification.email.EmailCanceledEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import com.hieplp.recipe.common.enums.notification.TemplateAction;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

@Saga
@Slf4j
public class RegistrationOtpSaga {

    private static final String OTP_ID = "otpId";
    private static final String LOG_ID = "logId";
    private static final String USER_ID = "userId";

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    public void handle(RegisterOtpCreatedEvent event) {
        try {
            log.info("Saga handles registration otp created event: {}", event);

            //
            SagaLifecycle.associateWith(OTP_ID, event.getOtpId());
            //
            SagaLifecycle.associateWith(USER_ID, event.getUserId());

            //
            var createTempUserCommand = CreateTempUserCommand.builder()
                    .userId(event.getUserId())
                    .username(event.getUsername())
                    .email(event.getEmail())
                    .fullName(event.getFullName())
                    .password(event.getPassword())
                    .createdBy(event.getUserId())
                    .referenceId(event.getOtpId())
                    .build();
            commandGateway.sendAndWait(createTempUserCommand);
        } catch (Exception e) {
            log.error("Error when handle registration otp created event: {}", event, e);
            cancelOtp(event.getOtpId(), event.getUserId());
        }
    }

    // -------------------------------------------------------------------------
    // XXX Temporary user is completely created
    // -------------------------------------------------------------------------
    @SagaEventHandler(associationProperty = USER_ID)
    private void handle(TempUserCompletedEvent event) {
        try {
            log.info("Saga handles temp user completed event: {}", event);

            var otp = queryGateway.query(new GetOtpQuery(event.getReferenceId()), OtpEntity.class).join();
            if (otp == null) {
                throw new IllegalArgumentException("Otp not found");
            }

            final var logId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith(LOG_ID, logId);

            // Init email params
            var params = new HashMap<String, String>();
            params.put("otpCode", otp.getOtpCode());

            // Send OTP via email
            commandGateway.send(SendEmailCommand.builder()
                    .logId(logId)
                    .action(TemplateAction.REGISTER.getAction())
                    .email(event.getEmail())
                    .params(params)
                    .referenceId(otp.getOtpId())
                    .createdBy(event.getUserId())
                    .build());
        } catch (Exception e) {
            log.error("Error when handle temp user completed event: {}", event, e);
            cancelOtp(event.getReferenceId(), event.getCreatedBy());
        }
    }

    // -------------------------------------------------------------------------
    // XXX Events from email
    // -------------------------------------------------------------------------
    @SagaEventHandler(associationProperty = LOG_ID)
    private void handle(EmailCompletedEvent event) {
        log.info("Saga handles email completed event: {}", event);
        commandGateway.send(CompleteRegisterOtpCommand.builder()
                .otpId(event.getReferenceId())
                .userId(event.getCreatedBy())
                .build());
    }

    @SagaEventHandler(associationProperty = LOG_ID)
    private void handle(EmailCanceledEvent event) {
        log.info("Saga handles email canceled event: {}", event);
        cancelOtp(event.getReferenceId(), event.getCreatedBy());
    }

    // -------------------------------------------------------------------------
    // XXX Complete otp
    // -------------------------------------------------------------------------
    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpCompletedEvent event) {
        log.info("Saga handles registration otp completed event: {}", event);
        SagaLifecycle.end();
    }

    // -------------------------------------------------------------------------
    // XXX Cancel otp
    // -------------------------------------------------------------------------
    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpCanceledEvent event) {
        log.info("Saga handles registration otp canceled event: {}", event);
        SagaLifecycle.end();
    }

    // -------------------------------------------------------------------------
    // XXX Private methods
    // -------------------------------------------------------------------------
    private void cancelOtp(String otpId, String userId) {
        var cancelOtpCommand = CancelRegisterOtpCommand.builder()
                .otpId(otpId)
                .userId(userId)
                .build();
        commandGateway.send(cancelOtpCommand);
    }
}
