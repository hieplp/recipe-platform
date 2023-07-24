package com.hieplp.recipe.auth.command.event.register;

import lombok.Data;

@Data
public class RegistrationOtpCreatedEvent {
    private String otpId;
    private String otpCode;
    private Byte status;
    private String username;
    private String email;
    private String userId;
}
