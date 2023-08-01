package com.hieplp.recipe.common.command.events.user.create;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class UserCreatedEvent {
    private String userId;
    private String username;
    private String fullName;
    private String email;
    private Byte status;
    private String createdBy;
    private LocalDateTime createdAt;
    private byte[] password;
    private byte[] salt;
}
