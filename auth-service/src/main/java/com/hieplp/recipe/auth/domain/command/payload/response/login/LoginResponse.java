package com.hieplp.recipe.auth.domain.command.payload.response.login;

import com.hieplp.recipe.common.entity.user.UserEntity;
import com.hieplp.recipe.common.payload.response.TokenResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private UserEntity user;
    private TokenResponse accessToken;
    private TokenResponse refreshToken;
}
