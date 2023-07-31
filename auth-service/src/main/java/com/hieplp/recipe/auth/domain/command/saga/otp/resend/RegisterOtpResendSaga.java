package com.hieplp.recipe.auth.domain.command.saga.otp.resend;

import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.RegisterOtpResentEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class RegisterOtpResendSaga extends OtpResendSaga<RegisterOtpResentEvent> {
    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(RegisterOtpResentEvent event) {
        try {
            log.info("Saga handles registration otp resent event: {}", event);
            final var otpHistoryId = generateOtpHistoryId();
            otpHelper.sendCreateOtpHistoryCommand(otpHistoryId, event);
        } catch (Exception e) {
            log.error("Error when handle registration otp resent event:", e);
            cancelOtpResend(event.getOtpId());
        }
    }

    @SagaEventHandler(associationProperty = OTP_HISTORY_ID)
    void handle(OtpHistoryCreationCompletedEvent event) {
        try {
            log.info("Saga handles otp history creation completed event: {}", event);
            final var logId = generateLogId();
            otpHelper.sendForgotOtp(event.getOtpId(), logId, event.getModifiedBy());
        } catch (Exception e) {
            log.error("Error when handle otp history creation completed event:", e);
            cancelOtpResend(event.getOtpId());
        }
    }
}
