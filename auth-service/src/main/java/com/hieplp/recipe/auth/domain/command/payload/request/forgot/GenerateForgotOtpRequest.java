package com.hieplp.recipe.auth.domain.command.payload.request.forgot;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GenerateForgotOtpRequest {
    @NotNull(message = "Email is required")
    private String email;
}
