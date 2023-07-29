package com.hieplp.recipe.auth.domain.command.event.otp.resent;

import lombok.Data;

@Data
public class RegisterOtpResentEvent {
    private String otpId;
    private String userId;
    private String otpCode;
}
