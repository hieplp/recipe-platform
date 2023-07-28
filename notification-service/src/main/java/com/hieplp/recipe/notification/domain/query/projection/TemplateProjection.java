package com.hieplp.recipe.notification.domain.query.projection;

import com.hieplp.recipe.notification.common.entity.TemplateEntity;
import com.hieplp.recipe.notification.common.repository.TemplateRepo;
import com.hieplp.recipe.notification.domain.query.queries.template.GetTemplateByActionAndSendViaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TemplateProjection {

    private final TemplateRepo templateRepo;

    @QueryHandler
    private TemplateEntity handle(GetTemplateByActionAndSendViaQuery query) {
        log.info("Get template by action and send via: {}", query);
        // TODO: Query from local cache first
        var optionalTemplate = templateRepo.getTemplateEntity(query.getAction(), query.getSendVia());
        return optionalTemplate.orElse(null);
    }
}
