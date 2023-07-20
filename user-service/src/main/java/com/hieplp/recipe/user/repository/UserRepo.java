package com.hieplp.recipe.user.repository;

import com.hieplp.recipe.common.jooq.base.BaseRepo;
import com.hieplp.recipe.user.repository.generate.tables.records.UserRecord;

public interface UserRepo extends BaseRepo {
    UserRecord getUserRecord(String userId);
}
