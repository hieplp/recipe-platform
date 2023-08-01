package com.hieplp.recipe.auth.domain.command.payload.request.login;


import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
