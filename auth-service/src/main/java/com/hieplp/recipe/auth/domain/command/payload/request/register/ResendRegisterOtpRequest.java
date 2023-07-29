package com.hieplp.recipe.auth.domain.command.payload.request.register;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResendRegisterOtpRequest {
    @NotNull(message = "OtpId is required")
    private String otpId;
}
