package com.hieplp.recipe.common.config.model;

import lombok.Data;

@Data
public class OtpConfig {
    private int quota;
    private int wrongQuota;
    private int expirationTime;
}