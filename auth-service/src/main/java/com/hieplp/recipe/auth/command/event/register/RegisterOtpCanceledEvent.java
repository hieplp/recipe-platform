package com.hieplp.recipe.auth.command.event.register;

import lombok.Data;

@Data
public class RegisterOtpCanceledEvent {
    private String otpId;
    private String userId;
    private Byte status;
}
