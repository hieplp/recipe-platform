package com.hieplp.recipe.auth.domain.command.event.otp.register.create;

import lombok.Data;

@Data
public class RegisterOtpCanceledEvent {
    private String otpId;
    private String userId;
    private Byte status;
}
