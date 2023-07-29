package com.hieplp.recipe.auth.domain.command.event.otp.resent;

import lombok.Data;

@Data
public class RegisterOtpResendCompletedEvent {
    private String otpId;
    private String userId;
}
