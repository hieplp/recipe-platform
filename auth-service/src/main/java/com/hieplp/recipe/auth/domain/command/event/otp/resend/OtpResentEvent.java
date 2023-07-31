package com.hieplp.recipe.auth.domain.command.event.otp.resend;

import lombok.Data;

@Data
public class OtpResentEvent {
    private String otpId;
    private String userId;
    private String otpCode;
}
