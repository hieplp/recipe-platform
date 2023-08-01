package com.hieplp.recipe.common.command.events.password.update;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasswordUpdatedEvent {
    private String userId;
    private Byte status;
    private byte[] password;
    private byte[] salt;
    private LocalDateTime modifiedAt;
}
