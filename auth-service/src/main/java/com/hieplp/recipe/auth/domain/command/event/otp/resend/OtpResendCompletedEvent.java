package com.hieplp.recipe.auth.domain.command.event.otp.resend;

import lombok.Data;

@Data
public class OtpResendCompletedEvent {
    private String otpId;
    private String userId;
}
