package com.hieplp.recipe.auth.common.entity;

import lombok.Data;

@Data
public class OtpEntity {
    private String otpId;
    private String otpCode;
    private String userId;
}
