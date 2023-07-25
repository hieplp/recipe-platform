package com.hieplp.recipe.auth.query.queries;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOtpQuery {
    private final String otpId;
}
