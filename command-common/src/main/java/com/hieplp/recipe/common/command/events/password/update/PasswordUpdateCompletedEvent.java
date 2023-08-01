package com.hieplp.recipe.common.command.events.password.update;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasswordUpdateCompletedEvent {
    private String userId;
    private Byte status;
    private LocalDateTime modifiedAt;
}
