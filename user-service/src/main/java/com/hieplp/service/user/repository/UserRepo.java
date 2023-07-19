package com.hieplp.service.user.repository;

import com.hieplp.recipe.common.jooq.base.BaseRepo;
import com.hieplp.service.user.repository.generate.tables.records.UserRecord;

public interface UserRepo extends BaseRepo {
    UserRecord getUserRecord(String userId);
}
