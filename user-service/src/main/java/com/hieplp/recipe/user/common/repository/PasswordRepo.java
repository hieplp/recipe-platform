package com.hieplp.recipe.user.common.repository;

import com.hieplp.recipe.common.entity.user.PasswordEntity;
import com.hieplp.recipe.common.jooq.base.BaseRepo;

import java.util.Optional;

public interface PasswordRepo extends BaseRepo {
    Optional<PasswordEntity> getPasswordByUserId(String userId);
}
