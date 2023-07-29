package com.hieplp.recipe.auth.domain.command.saga.otp;

import com.hieplp.recipe.auth.domain.command.commands.otp.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.CancelRegisterOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.commands.otp.register.resend.CompleteRegisterOtpResendCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.history.create.OtpHistoryCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCanceledEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResendCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resent.RegisterOtpResentEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import com.hieplp.recipe.common.command.events.notification.email.EmailCanceledEvent;
import com.hieplp.recipe.common.command.events.notification.email.EmailCompletedEvent;
import com.hieplp.recipe.common.enums.IdLength;
import com.hieplp.recipe.common.enums.otp.OtpHistoryStatus;
import com.hieplp.recipe.common.enums.otp.OtpHistoryType;
import com.hieplp.recipe.common.util.GeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class RegisterOtpResendSaga {
    private static final String OTP_ID = "otpId";
    private static final String OTP_HISTORY_ID = "otpHistoryId";
    private static final String LOG_ID = "logId";

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient OtpHelper otpHelper;


    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpResentEvent event) {
        try {
            log.info("Saga handles registration otp resent event: {}", event);

            final var otpHistoryId = GeneratorUtil.generateId(IdLength.OTP_HISTORY_ID);
            SagaLifecycle.associateWith(OTP_HISTORY_ID, otpHistoryId);

            var createOtpHistoryCommand = CreateOtpHistoryCommand.builder()
                    .otpHistoryId(otpHistoryId)
                    .otpId(event.getOtpId())
                    .otpCode(event.getOtpCode())
                    .type(OtpHistoryType.RESEND.getType())
                    .status(OtpHistoryStatus.INIT.getStatus())
                    .createdBy("anonymous")
                    .build();
            commandGateway.send(createOtpHistoryCommand);
        } catch (Exception e) {
            log.error("Error when handle registration otp resent event:", e);
            cancelOtpResend(event.getOtpId());
        }
    }

    @SagaEventHandler(associationProperty = OTP_HISTORY_ID)
    private void handle(OtpHistoryCreationCompletedEvent event) {
        try {
            log.info("Saga handles otp history creation completed event: {}", event);

            final var logId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith(LOG_ID, logId);

            otpHelper.sendRegisterOtp(event.getOtpId(), logId, event.getModifiedBy());
        } catch (Exception e) {
            log.error("Error when handle otp history creation completed event:", e);
            cancelOtpResend(event.getOtpId());
        }
    }

    @SagaEventHandler(associationProperty = LOG_ID)
    private void handle(EmailCompletedEvent event) {
        try {
            log.info("Saga handles email completed event: {}", event);
            var completeCommand = CompleteRegisterOtpResendCommand.builder()
                    .otpId(event.getReferenceId())
                    .build();
            commandGateway.send(completeCommand);
        } catch (Exception e) {
            log.error("Error when handle email completed event:", e);
            cancelOtpResend(event.getReferenceId());
        }
    }

    @SagaEventHandler(associationProperty = LOG_ID)
    private void handle(EmailCanceledEvent event) {
        log.info("Saga handles email canceled event: {}", event);
        cancelOtpResend(event.getReferenceId());
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpResendCompletedEvent event) {
        log.info("Saga handles registration otp resend completed event: {}", event);
        SagaLifecycle.end();
    }

    @EndSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    private void handle(RegisterOtpResendCanceledEvent event) {
        log.info("Saga handles registration otp resend canceled event: {}", event);
        SagaLifecycle.end();
    }

    private void cancelOtpResend(String otpId) {
        commandGateway.send(CancelRegisterOtpResendCommand.builder()
                .otpId(otpId)
                .build());
    }
}
