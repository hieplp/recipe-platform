package com.hieplp.recipe.auth.domain.query.queries.otp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTodayOtpQuotaQuery {
    private final String sendTo;
    private final Byte type;
}
