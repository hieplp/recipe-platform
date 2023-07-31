package com.hieplp.recipe.auth.domain.command.helper;

import com.hieplp.recipe.auth.domain.command.commands.history.create.CreateOtpHistoryCommand;
import com.hieplp.recipe.auth.domain.command.event.otp.resend.OtpResentEvent;
import com.hieplp.recipe.common.enums.notification.TemplateAction;

public interface OtpHelper {
    // -------------------------------------------------------------------------
    // XXX Send OTP
    // -------------------------------------------------------------------------
    void sendRegisterOtp(String otpId, String logId, String sentBy);

    void sendForgotOtp(String otpId, String logId, String sentBy);

    void sendOtp(String otpId,
                 String logId,
                 String sendBy,
                 TemplateAction action);

    // -------------------------------------------------------------------------
    // XXX OTP History
    // -------------------------------------------------------------------------

    void sendCreateOtpHistoryCommand(String otpHistoryId, OtpResentEvent event);

    void sendCreateOtpHistoryCommand(CreateOtpHistoryCommand command);
}
