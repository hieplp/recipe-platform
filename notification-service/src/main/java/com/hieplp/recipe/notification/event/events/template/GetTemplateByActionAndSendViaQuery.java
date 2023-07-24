package com.hieplp.recipe.notification.event.events.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTemplateByActionAndSendViaQuery {
    private final String action;
    private final Byte sendVia;
}
