package com.hieplp.recipe.common.command.events.notification.email;

import lombok.Data;

import java.util.Map;

@Data
public class EmailSentEvent {
    private String sendTo;
    private String action;
    private Map<String, String> params;
    private String createdBy;
    private String logId;
    private Byte status;
}
