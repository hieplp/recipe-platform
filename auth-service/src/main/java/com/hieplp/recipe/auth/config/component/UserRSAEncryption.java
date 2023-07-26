package com.hieplp.recipe.auth.config.component;

import com.hieplp.recipe.auth.config.model.AuthConfig;
import com.hieplp.recipe.common.config.model.RsaConfig;
import com.hieplp.recipe.common.exception.UnknownException;
import com.hieplp.recipe.common.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

@Component
@Slf4j
public class UserRSAEncryption {

    private final RsaConfig rsaConfig;
    private final KeyFactory kf;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    private UserRSAEncryption(AuthConfig authConfig) {
        try {
            this.rsaConfig = authConfig.getRsa();
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            log.error("Error when init encryption caused by {}", e.getMessage());
            throw new UnknownException("Error when init encryption");
        }
    }

    @PostConstruct
    private void init() {
        try {
            // Private key
            var privateBytes = FileUtil.decodeByteFile(rsaConfig.getPrivateKey());
            var privateSpec = new PKCS8EncodedKeySpec(privateBytes);
            privateKey = kf.generatePrivate(privateSpec);

            // Public key
            var kf = KeyFactory.getInstance("RSA");
            var rsaPrivateCrtKey = (RSAPrivateCrtKey) getPrivateKey();
            var publicSpec = new RSAPublicKeySpec(rsaPrivateCrtKey.getModulus(), rsaPrivateCrtKey.getPublicExponent());
            publicKey = kf.generatePublic(publicSpec);
        } catch (Exception e) {
            log.error("Error when init encrypt: {}", e.getMessage());
            throw new UnknownException(e.getMessage());
        }
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}
