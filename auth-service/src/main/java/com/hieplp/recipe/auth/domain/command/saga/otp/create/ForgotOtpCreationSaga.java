package com.hieplp.recipe.auth.domain.command.saga.otp.create;

import com.hieplp.recipe.auth.domain.command.event.otp.create.ForgotOtpCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.UUID;

@Saga
@Slf4j
public class ForgotOtpCreationSaga extends OtpCreationSaga {
    @StartSaga
    @SagaEventHandler(associationProperty = OTP_ID)
    void handle(ForgotOtpCreatedEvent event) {
        try {
            log.info("Saga handles forgot otp created event: {}", event);

            this.otpId = event.getOtpId();
            this.logId = UUID.randomUUID().toString();

            SagaLifecycle.associateWith(LOG_ID, logId);


            log.warn("dklsadklsajdksakldjsakldjsakdsakldsakdsa");

            otpHelper.sendForgotOtp(event.getOtpId(), logId, event.getSendTo());
        } catch (Exception e) {
            log.error("Error when handle forgot otp created event:", e);
            cancelOtpCreation();
        }
    }
}