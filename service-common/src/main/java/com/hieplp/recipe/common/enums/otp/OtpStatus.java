package com.hieplp.recipe.common.enums.otp;

public enum OtpStatus {
    CREATED(0),
    ACTIVATED(1),
    CANCELED(2),
    CONFIRMED(3)
    ;

    private final Byte status;

    OtpStatus(Integer status) {
        this.status = status.byteValue();
    }

    public Byte getStatus() {
        return status;
    }
}
