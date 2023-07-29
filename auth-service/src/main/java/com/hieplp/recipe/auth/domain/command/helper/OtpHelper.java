package com.hieplp.recipe.auth.domain.command.helper;

public interface OtpHelper {
    void sendRegisterOtp(String otpId, String logId, String sentBy);
}
