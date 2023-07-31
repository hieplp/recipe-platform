package com.hieplp.recipe.auth.domain.command.payload.request.forgot;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResendForgotOtpRequest {
    @NotNull(message = "OtpId is required")
    private String otpId;
}
