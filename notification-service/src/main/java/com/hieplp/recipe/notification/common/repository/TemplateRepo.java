package com.hieplp.recipe.notification.common.repository;

import com.hieplp.recipe.common.jooq.base.BaseRepo;
import com.hieplp.recipe.notification.common.entity.TemplateEntity;

import java.util.Optional;

public interface TemplateRepo extends BaseRepo {
    Optional<TemplateEntity> getTemplateEntity(String action, Byte sendVia);
}
