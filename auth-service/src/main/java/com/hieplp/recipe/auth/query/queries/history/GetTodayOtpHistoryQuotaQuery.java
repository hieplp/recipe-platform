package com.hieplp.recipe.auth.query.queries.history;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTodayOtpHistoryQuotaQuery {
    private final String otpId;
    private final Byte type;
}
