package com.hieplp.recipe.common.entity.auth;

import lombok.Data;

@Data
public class TempUserEntity {
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private byte[] password;
    private byte[] salt;
}
