package com.hieplp.recipe.auth.domain.command.payload.request.forgot;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConfirmForgotOtpRequest {
    @NotNull(message = "OtpId is required")
    private String otpId;
    @NotNull(message = "OtpCode is required")
    private String otpCode;
    @NotNull(message = "Password is required")
    private String password;
}
