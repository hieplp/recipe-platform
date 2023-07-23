package com.hieplp.recipe.auth.command.event.register;

import lombok.Data;

@Data
public class RegisterOtpCreatedEvent {
    private String otpId;
    private String username;
    private String email;
}
