package com.hieplp.recipe.common.enums.user;

public enum UserStatus {
    INACTIVE(0),
    ACTIVE(1),
    ;

    private final Byte status;

    UserStatus(Integer status) {
        this.status = status.byteValue();
    }

    public Byte getStatus() {
        return status;
    }
}
