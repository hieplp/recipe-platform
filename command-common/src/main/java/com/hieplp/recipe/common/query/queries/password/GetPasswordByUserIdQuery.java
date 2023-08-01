package com.hieplp.recipe.common.query.queries.password;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPasswordByUserIdQuery {
    private final String userId;
}
