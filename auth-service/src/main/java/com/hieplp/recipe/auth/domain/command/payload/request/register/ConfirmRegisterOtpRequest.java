package com.hieplp.recipe.auth.domain.command.payload.request.register;

import lombok.Data;

@Data
public class ConfirmRegisterOtpRequest {
    private String otpId;
    private String otpCode;
}
