package com.hieplp.recipe.common.enums.notification;

public enum SendVia {
    EMAIL(0),
    ;

    private final Byte sendVia;

    SendVia(Integer sendVia) {
        this.sendVia = sendVia.byteValue();
    }

    public Byte getSendVia() {
        return sendVia;
    }
}
