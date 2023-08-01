package com.hieplp.recipe.user.common.repository;

import com.hieplp.recipe.common.entity.user.UserEntity;
import com.hieplp.recipe.common.jooq.base.BaseRepo;
import com.hieplp.recipe.user.common.repository.generate.tables.records.UserRecord;

import java.util.Optional;

public interface UserRepo extends BaseRepo {
    UserRecord getUserRecord(String userId);

    boolean doesUsernameExist(String username);

    boolean doesEmailExist(String email);

    Optional<String> getUserIdByEmail(String email);

    Optional<UserEntity> getUserByUsername(String username);

    Optional<UserEntity> getUserByUserId(String userId);
}
