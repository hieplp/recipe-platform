package com.hieplp.recipe.common.command.events.notification.email;

import lombok.Data;

@Data
public class EmailCompletedEvent {
    private String logId;
    private Byte status;
    private String createdBy;
}
