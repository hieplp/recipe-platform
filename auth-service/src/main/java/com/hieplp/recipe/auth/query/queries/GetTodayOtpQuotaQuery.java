package com.hieplp.recipe.auth.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTodayOtpQuotaQuery {
    private final String sendTo;
    private final Byte type;
}
