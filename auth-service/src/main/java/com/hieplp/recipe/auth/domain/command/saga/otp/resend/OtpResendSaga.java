package com.hieplp.recipe.auth.domain.command.saga.otp.resend;

import com.hieplp.recipe.auth.domain.command.commands.otp.resend.CancelOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.resend.CompleteOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResendCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResendCompletedEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.common.command.events.notification.email.EmailCanceledEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import com.hieplp.recipe.common.enums.IdLength;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
public abstract class OtpResendSaga {

    static final String OTP_ID = "otpId";
    static final String OTP_HISTORY_ID = "otpHistoryId";
    static final String LOG_ID = "logId";

    @Autowired
    protected transient CommandGateway commandGateway;
    @Autowired
    protected transient OtpHelper otpHelper;

    String otpId;
    String otpHistoryId;

    @SagaEventHandler(associationProperty = OTP_HISTORY_ID)
    abstract void handle(OtpHistoryCreationCompletedEvent event);

    @SagaEventHandler(associationProperty = LOG_ID)
    void handle(EmailCompletedEvent event) {
        try {
            log.info("Saga handles email completed event: {}", event);
            var completeCommand = CompleteOtpResendCommand.builder()
                    .otpId(this.otpId)
                    .build();
            commandGateway.send(completeCommand);
        } catch (Exception e) {
            log.error("Error when handle email completed event:", e);
            cancelOtpResend();
        }
    }

    @SagaEventHandler(associationProperty = LOG_ID)
    void handle(EmailCanceledEvent event) {
        try {
            log.info("Saga handles email canceled event: {}", event);
            cancelOtpResend();
        } catch (Exception e) {
            log.error("Error when handle email canceled event:", e);
            cancelOtpResend();
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(OtpResendCompletedEvent event) {
        log.info("Saga handles otp resend completed event: {}", event);
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(OtpResendCanceledEvent event) {
        log.info("Saga handles otp resend canceled event: {}", event);
        SagaLifecycle.end();
    }

    void cancelOtpResend() {
        commandGateway.send(CancelOtpResendCommand.builder()
                .otpId(this.otpId)
                .build());
    }

    String generateOtpHistoryId() {
        final var otpHistoryId = GeneratorUtil.generateId(IdLength.OTP_HISTORY_ID);
        SagaLifecycle.associateWith(OTP_HISTORY_ID, otpHistoryId);
        return otpHistoryId;
    }

    String generateLogId() {
        final var logId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith(LOG_ID, logId);
        return logId;
    }
}
