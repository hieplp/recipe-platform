package com.hieplp.recipe.auth.domain.command.event.otp.register.resend;

import lombok.Data;

@Data
public class RegisterOtpResentEvent {
    private String otpId;
    private String otpCode;
}
