package com.hieplp.recipe.auth.common.repository;

import com.hieplp.recipe.common.entity.auth.TempUserEntity;
import com.hieplp.recipe.common.jooq.base.BaseRepo;

import java.util.Optional;

public interface TempUserRepo extends BaseRepo {
    Optional<TempUserEntity> getTempUserByOtpId(String userId);
}
