package com.hieplp.recipe.auth.domain.command.saga.otp.confirm;

import com.hieplp.recipe.auth.domain.command.event.otp.confirm.ForgotOtpConfirmedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class ForgotOtpConfirmationSaga extends OtpConfirmationSaga<ForgotOtpConfirmedEvent> {
}
