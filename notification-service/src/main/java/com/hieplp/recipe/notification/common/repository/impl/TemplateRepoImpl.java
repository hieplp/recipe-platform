package com.hieplp.recipe.notification.common.repository.impl;

import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import com.hieplp.recipe.notification.common.entity.TemplateEntity;
import com.hieplp.recipe.notification.common.repository.TemplateRepo;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.hieplp.recipe.notification.common.repository.generate.Tables.TEMPLATE;

@Repository
@Slf4j
public class TemplateRepoImpl extends BaseRepoImpl implements TemplateRepo {
    public TemplateRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public Optional<TemplateEntity> getTemplateEntity(String action, Byte sendVia) {
        log.info("Get template entity by action {} and sendVia {}", action, sendVia);
        return fetchOne(TEMPLATE, TEMPLATE.ACTION.eq(action).and(TEMPLATE.SENDVIA.eq(sendVia)), TemplateEntity.class);
    }
}
