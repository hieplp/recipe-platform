package com.hieplp.recipe.auth.domain.command.event.user;

import lombok.Data;

@Data
public class TempUserCompletedEvent {
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private Byte status;
    private String createdBy;
    private String referenceId;
}
