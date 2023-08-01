package com.hieplp.recipe.auth.domain.command.event.user;

import lombok.Data;

@Data
public class TempUserCreatedEvent {
    private String userId;
    private Byte status;
    private String username;
    private String email;
    private String fullName;
    private byte[] password;
    private byte[] salt;
    private String createdBy;
    private String referenceId;
}
