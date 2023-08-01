package com.hieplp.recipe.auth.domain.command.event.otp.confirm;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class OtpConfirmedEvent {
    private String otpId;
    private Byte status;
    private String userId;
    private LocalDateTime modifiedAt;
}
