package com.hieplp.recipe.common.entity.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasswordEntity {
    private String userId;
    private byte[] password;
    private byte[] salt;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
