package com.hieplp.recipe.auth.config.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.auth")
public class AuthConfig {
    private OtpConfig registerOtp;
    private RsaConfig rsa;

    @Data
    public static class RsaConfig {
        private String publicKey;
        private String privateKey;
    }
}
