package com.hieplp.recipe.notification.domain.query.queries.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTemplateByActionAndSendViaQuery {
    private final String action;
    private final Byte sendVia;
}
