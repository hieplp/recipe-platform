package com.hieplp.recipe.common.enums.otp;

import lombok.Getter;

@Getter
public enum OtpHistoryStatus {
    INIT(0),
    CANCELED(1),
    CORRECT(2),
    WRONG(3),
    ;

    private final Byte status;

    OtpHistoryStatus(Integer status) {
        this.status = status.byteValue();
    }

}
