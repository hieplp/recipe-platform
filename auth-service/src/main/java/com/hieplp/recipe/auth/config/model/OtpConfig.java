package com.hieplp.recipe.auth.config.model;

import lombok.Data;

@Data
public class OtpConfig {
    private Integer quota;
    private Integer expirationTime;
}