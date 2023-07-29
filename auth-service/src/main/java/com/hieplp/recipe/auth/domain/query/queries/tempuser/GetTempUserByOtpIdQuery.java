package com.hieplp.recipe.auth.domain.query.queries.tempuser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTempUserByOtpIdQuery {
    private final String otpId;
}
