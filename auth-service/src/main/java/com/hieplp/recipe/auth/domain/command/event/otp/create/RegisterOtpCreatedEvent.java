package com.hieplp.recipe.auth.domain.command.event.otp.create;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterOtpCreatedEvent extends OtpCreatedEvent {
    private String username;
    private String password;
    private String fullName;
    private String userId;
}
