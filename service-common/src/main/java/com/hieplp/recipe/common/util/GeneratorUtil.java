package com.hieplp.recipe.common.util;

import java.util.concurrent.ThreadLocalRandom;

public class GeneratorUtil {
    public static String generateOTP(int length) {
        // Define the characters allowed in the OTP
        String allowedChars = "0123456789";
        StringBuilder otp = new StringBuilder();

        // Build the OTP by selecting random characters from the allowedChars string
        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(allowedChars.length());
            otp.append(allowedChars.charAt(index));
        }

        return otp.toString();
    }
}
