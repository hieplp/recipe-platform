package com.hieplp.recipe.auth.domain.command.helper;

import com.hieplp.recipe.common.enums.notification.TemplateAction;

public interface OtpHelper {
    void sendRegisterOtp(String otpId, String logId, String sentBy);

    void sendForgotOtp(String otpId, String logId, String sentBy);

    void sendOtp(String otpId, String logId, String sendBy, TemplateAction action);
}
