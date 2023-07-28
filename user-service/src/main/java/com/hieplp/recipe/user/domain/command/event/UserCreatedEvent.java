package com.hieplp.recipe.user.domain.command.event;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private String userId;
    private String username;
    private String fullName;
    private Byte status;
    private String createdBy;
    private Long createdAt;
    private String modifiedBy;
    private Long modifiedAt;
}
