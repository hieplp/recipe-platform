package com.hieplp.recipe.common.entity.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntity {
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private Byte status;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
