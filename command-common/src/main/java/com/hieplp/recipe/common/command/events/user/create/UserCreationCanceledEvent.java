package com.hieplp.recipe.common.command.events.user.create;

import lombok.Data;

@Data
public class UserCreationCanceledEvent {
    private String userId;
    private Byte status;
    private String referenceId;
}
