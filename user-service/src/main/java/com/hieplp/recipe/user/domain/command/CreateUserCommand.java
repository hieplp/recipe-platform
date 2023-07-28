package com.hieplp.recipe.user.domain.command;

import lombok.Data;

@Data
public class CreateUserCommand {
    private String userId;
    private String username;
    private String fullName;
}
