package com.hieplp.recipe.notification.query.queries.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTemplateByActionAndSendViaQuery {
    private final String action;
    private final Byte sendVia;
}
