package com.hieplp.recipe.common.util;

import com.hieplp.recipe.common.exception.UnknownException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class EncryptUtil {

    public static byte[] decrypt(byte[] buffer, PrivateKey privateKey) {
        try {
            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.DECRYPT_MODE, privateKey);
            return rsa.doFinal(buffer);
        } catch (Exception e) {
            log.error("Error when decrypt RSA string caused by {}", e.getMessage());
            throw new UnknownException("Error on decrypt password.");
        }
    }

    public static byte[] generatePassword(String password, PrivateKey privateKey, byte[] salt) {
        try {
            log.info("Start generate password");
            log.info("Pasword:{}", password);
            byte[] rawPassword = decrypt(Base64.getDecoder().decode(password), privateKey);
            rawPassword = GeneratorUtil.hash(ConverterUtil.toCharArray(rawPassword), salt);
            return rawPassword;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownException(e.getMessage());
        }
    }

    public static boolean validatePassword(String inputPassword, byte[] userPassword, byte[] salt, PrivateKey privateKey) {
        log.info("Start validate password");
        byte[] rawPassword = new byte[0];
        try {
            rawPassword = generatePassword(inputPassword, privateKey, salt);
            return Arrays.equals(rawPassword, userPassword);
        } catch (Exception e) {
            log.error("Error when validate password caused by {}", e.getMessage());
            return false;
        } finally {
            // Clear password in memory for security
            Arrays.fill(rawPassword, Byte.MIN_VALUE);
            Arrays.fill(userPassword, Byte.MIN_VALUE);
        }
    }
}
