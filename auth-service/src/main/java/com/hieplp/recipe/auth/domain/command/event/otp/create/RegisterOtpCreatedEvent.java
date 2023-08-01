package com.hieplp.recipe.auth.domain.command.event.otp.create;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RegisterOtpCreatedEvent extends OtpCreatedEvent {
    private String username;
    private byte[] password;
    private byte[] salt;
    private String fullName;
}
