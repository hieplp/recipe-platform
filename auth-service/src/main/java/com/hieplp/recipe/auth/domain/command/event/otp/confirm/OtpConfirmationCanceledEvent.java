package com.hieplp.recipe.auth.domain.command.event.otp.confirm;

import lombok.Data;

@Data
public class OtpConfirmationCanceledEvent {
    private String otpId;
    private Byte status;
    private String modifiedBy;
}
