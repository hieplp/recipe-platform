package com.hieplp.recipe.auth.config.model;

import com.hieplp.recipe.common.config.model.OtpConfig;
import com.hieplp.recipe.common.config.model.RsaConfig;
import com.hieplp.recipe.common.config.model.TokenConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.auth")
public class AuthConfig {
    private OtpConfig registerOtp;
    private OtpConfig forgotOtp;
    private RsaConfig rsa;
    private TokenConfig accessToken;
    private TokenConfig refreshToken;
}
