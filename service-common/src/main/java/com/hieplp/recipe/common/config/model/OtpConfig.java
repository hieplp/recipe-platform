package com.hieplp.recipe.common.config.model;

import lombok.Data;

@Data
public class OtpConfig {
    private Integer quota;
    private Integer wrongQuota;
    private Integer expirationTime;
}