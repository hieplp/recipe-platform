package com.hieplp.recipe.auth.command.payload.request.register;

import lombok.Data;

@Data
public class VerifyRegisterOtpRequest {
    private String otpId;
    private String otpCode;
}
