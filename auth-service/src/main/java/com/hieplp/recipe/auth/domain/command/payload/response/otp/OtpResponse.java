package com.hieplp.recipe.auth.domain.command.payload.response.otp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpResponse {
    private String otpId;
    private String maskedEmail;
    private String expiredAt;
    private Integer expiredIn;
}
