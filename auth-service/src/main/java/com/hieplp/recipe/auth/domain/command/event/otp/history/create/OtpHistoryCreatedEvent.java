package com.hieplp.recipe.auth.domain.command.event.otp.history.create;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OtpHistoryCreatedEvent {
    private String otpHistoryId;
    private String otpId;
    private String otpCode;
    private Byte status;
    private Byte type;
    private String createdBy;
    private LocalDateTime createdAt;
}
