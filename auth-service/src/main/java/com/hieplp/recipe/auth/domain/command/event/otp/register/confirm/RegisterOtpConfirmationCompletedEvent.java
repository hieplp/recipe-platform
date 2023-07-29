package com.hieplp.recipe.auth.domain.command.event.otp.register.confirm;

import lombok.Data;

@Data
public class RegisterOtpConfirmationCompletedEvent {
    private String otpId;
    private Byte status;
    private String modifiedBy;
}
