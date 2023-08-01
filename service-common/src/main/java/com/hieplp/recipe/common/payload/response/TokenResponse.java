package com.hieplp.recipe.common.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TokenResponse {
    private String token;
    private LocalDateTime expiredAt;
}
