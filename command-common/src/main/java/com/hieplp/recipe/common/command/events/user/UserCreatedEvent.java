package com.hieplp.recipe.common.command.events.user;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private String userId;
    private String username;
    private String email;
}
