package com.hieplp.recipe.common.command.events.notification.email;

import lombok.Data;

@Data
public class EmailCanceledEvent {
    private String logId;
    private Byte status;
}
