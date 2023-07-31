package com.hieplp.recipe.auth.domain.command.event.history.create;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OtpHistoryCreationCanceledEvent {
    private String otpHistoryId;
    private Byte status;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
