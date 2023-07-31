package com.hieplp.recipe.auth.domain.command.saga.otp.confirm;

import com.hieplp.recipe.auth.domain.command.event.otp.confirm.OtpConfirmedEvent;
import com.hieplp.recipe.auth.domain.command.helper.OtpHelper;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class OtpConfirmationSaga<T extends OtpConfirmedEvent> {
    static final String OTP_ID = "otpId";
    static final String OTP_HISTORY_ID = "otpHistoryId";
    static final String LOG_ID = "logId";

    @Autowired
    protected transient CommandGateway commandGateway;
    @Autowired
    protected transient OtpHelper otpHelper;
}
