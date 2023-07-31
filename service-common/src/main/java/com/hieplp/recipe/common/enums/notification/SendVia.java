package com.hieplp.recipe.common.enums.notification;

import lombok.Getter;

@Getter
public enum SendVia {
    EMAIL(0),
    ;

    private final Byte sendVia;

    SendVia(Integer sendVia) {
        this.sendVia = sendVia.byteValue();
    }
}
