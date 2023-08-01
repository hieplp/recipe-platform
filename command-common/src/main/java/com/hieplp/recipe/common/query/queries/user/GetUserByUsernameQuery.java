package com.hieplp.recipe.common.query.queries.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserByUsernameQuery {
    private final String username;
}
