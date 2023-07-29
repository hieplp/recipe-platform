package com.hieplp.recipe.auth.domain.command.event.otp.register.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOtpCompletedEvent {
    private String otpId;
    private Byte status;
    private String userId;
}
