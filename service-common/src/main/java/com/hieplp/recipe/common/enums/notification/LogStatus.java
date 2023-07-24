package com.hieplp.recipe.common.enums.notification;

public enum LogStatus {
    INIT(0),
    SUCCESS(1),
    FAILED(2);

    private final Byte status;

    LogStatus(Integer status) {
        this.status = status.byteValue();
    }

    public Byte getStatus() {
        return status;
    }
}
