package com.hieplp.recipe.common.enums.otp;

public enum OtpType {
    REGISTER(0),
    FORGOT_PASSWORD(1)
    ;

    private final Byte type;

    OtpType(Integer type) {
        this.type = type.byteValue();
    }

    public Byte getType() {
        return type;
    }
}
