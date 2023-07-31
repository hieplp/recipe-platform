package com.hieplp.recipe.auth.domain.command.saga.otp.resend;

import com.hieplp.recipe.auth.domain.command.event.history.create.OtpHistoryCreationCompletedEvent;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.ForgotOtpResentEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class ForgotOtpResendSaga extends OtpResendSaga<ForgotOtpResentEvent> {
    @Override
    void handle(ForgotOtpResentEvent event) {
        try {
            log.info("Saga handles forgot otp resent event: {}", event);
            final var otpHistoryId = generateOtpHistoryId();
            otpHelper.sendCreateOtpHistoryCommand(otpHistoryId, event);
        } catch (Exception e) {
            log.error("Error when handle forgot otp resent event:", e);
            cancelOtpResend(event.getOtpId());
        }
    }

    @Override
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
