package com.hieplp.recipe.common.enums.user;

public enum TempUserStatus {
    PENDING(0),
    ACTIVE(1),
    CANCELED(2),
    ;

    private final Byte status;

    TempUserStatus(Integer status) {
        this.status = status.byteValue();
    }

    public Byte getStatus() {
        return status;
    }
}
