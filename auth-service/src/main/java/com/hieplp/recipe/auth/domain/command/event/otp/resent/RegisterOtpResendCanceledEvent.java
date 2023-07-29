package com.hieplp.recipe.auth.domain.command.event.otp.resent;

import lombok.Data;

@Data
public class RegisterOtpResendCanceledEvent {
    private String otpHistoryId;
    private String otpId;
}
