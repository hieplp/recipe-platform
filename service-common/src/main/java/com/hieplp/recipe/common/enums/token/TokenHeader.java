package com.hieplp.recipe.common.enums.token;

import lombok.Getter;

@Getter
public enum TokenHeader {
    USER_ID("userId"),
    ;

    private final String header;

    TokenHeader(String header) {
        this.header = header;
    }
}
