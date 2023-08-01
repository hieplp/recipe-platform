package com.hieplp.recipe.common.util;

import com.hieplp.recipe.common.config.model.TokenConfig;
import com.hieplp.recipe.common.entity.user.UserEntity;
import com.hieplp.recipe.common.enums.token.TokenHeader;
import com.hieplp.recipe.common.enums.token.TokenType;
import com.hieplp.recipe.common.payload.response.TokenResponse;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.security.PrivateKey;
import java.util.HashMap;

@Slf4j
public class TokenUtil {
    public static TokenResponse generateToken(TokenConfig tokenConfig,
                                              PrivateKey privateKey,
                                              TokenType tokenType,
                                              UserEntity user) {
        log.info("Generating token with user: {}, tokenType: {} and tokenConfig: {}", user, tokenType, tokenConfig);

        // Headers
        var headers = new HashMap<String, Object>();
        headers.put(TokenHeader.USER_ID.getHeader(), user.getUserId());

        // Expiration time
        var currentDate = DateUtil.getCurrentDate();
        var expiredAt = DateUtil.addSeconds(currentDate, tokenConfig.getActiveTime());

        // JWT builder
        var jwt = Jwts.builder()
                .setHeader(headers)
                .setAudience(user.getUserId())
                .setExpiration(expiredAt)
                .setIssuedAt(currentDate)
                .signWith(privateKey)
                .compact();

        return TokenResponse.builder()
                .token(jwt)
                .expiredAt(DateUtil.toLocalDateTime(expiredAt))
                .build();
    }
}
