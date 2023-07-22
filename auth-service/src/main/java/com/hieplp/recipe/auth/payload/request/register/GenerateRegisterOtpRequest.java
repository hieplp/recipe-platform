package com.hieplp.recipe.auth.payload.request.register;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class GenerateRegisterOtpRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
}
