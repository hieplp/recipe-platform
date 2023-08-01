package com.hieplp.recipe.common.enums.otp;

import lombok.Getter;

@Getter
public enum OtpStatus {
    CREATED(0),
    ACTIVATED(1),
    CANCELED(2),
    CONFIRMED(3);

    private final Byte status;

    OtpStatus(Integer status) {
        this.status = status.byteValue();
    }
}
