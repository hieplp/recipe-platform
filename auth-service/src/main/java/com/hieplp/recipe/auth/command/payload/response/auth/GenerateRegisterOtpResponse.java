package com.hieplp.recipe.auth.command.payload.response.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateRegisterOtpResponse {
    private String otpId;
    private String maskedEmail;
    private Integer expiredIn;
}
