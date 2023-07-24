package com.hieplp.recipe.auth.command.event.register;

import lombok.Builder;
import lombok.Data;

@Data
public class RegistrationOtpCompletedEvent {
    private String otpId;
    private Byte status;
    private String userId;
}
