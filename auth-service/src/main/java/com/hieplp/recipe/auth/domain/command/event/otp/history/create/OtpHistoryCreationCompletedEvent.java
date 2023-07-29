package com.hieplp.recipe.auth.domain.command.event.otp.history.create;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OtpHistoryCreationCompletedEvent {
    private String otpHistoryId;
    private String otpId;
    private Byte status;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
