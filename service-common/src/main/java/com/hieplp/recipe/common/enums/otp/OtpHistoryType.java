package com.hieplp.recipe.common.enums.otp;

public enum OtpHistoryType {
    CONFIRM(0),
    RESEND(1);

    private final Byte type;

    OtpHistoryType(Integer type) {
        this.type = type.byteValue();
    }

    public Byte getType() {
        return type;
    }
}
