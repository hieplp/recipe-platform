package com.hieplp.recipe.auth.domain.command.event.otp.resend;

import lombok.Data;

@Data
public class OtpResendCanceledEvent {
    private String otpHistoryId;
    private String otpId;
}
