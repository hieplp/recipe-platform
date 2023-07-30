package com.hieplp.recipe.auth.domain.command.event.otp.create;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OtpCreationCompletedEvent {
    private String otpId;
    private Byte status;
    private LocalDateTime modifiedAt;
}
