package com.hieplp.recipe.common.command.events.password.update;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasswordUpdateCanceledEvent {
    private String userId;
    private LocalDateTime modifiedAt;
}
