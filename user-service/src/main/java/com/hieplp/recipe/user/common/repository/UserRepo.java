package com.hieplp.recipe.user.common.repository;

import com.hieplp.recipe.common.jooq.base.BaseRepo;
import com.hieplp.recipe.user.common.repository.generate.tables.records.UserRecord;

public interface UserRepo extends BaseRepo {
    UserRecord getUserRecord(String userId);

    boolean doesUsernameExist(String username);

    boolean doesEmailExist(String email);
}
