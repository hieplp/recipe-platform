package com.hieplp.recipe.common.enums;

public enum IdLength {
    USER_ID(10),
    OTP_ID(10),
    OTP_HISTORY_ID(10);

    private final int length;

    IdLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
