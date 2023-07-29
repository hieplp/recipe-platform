package com.hieplp.recipe.user.common.repository.impl;

import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import com.hieplp.recipe.user.common.repository.UserRepo;
import com.hieplp.recipe.user.common.repository.generate.Tables;
import com.hieplp.recipe.user.common.repository.generate.tables.records.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepoImpl extends BaseRepoImpl implements UserRepo {
    public UserRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public UserRecord getUserRecord(String userId) {
        log.info("Get user record by userId {}", userId);
        return fetchOneNotNull(Tables.USER_, Tables.USER_.USERID.eq(userId));
    }

    @Override
    public boolean doesUsernameExist(String username) {
        log.info("Check if username exists: {}", username);
        return fetchExist(Tables.USER_, Tables.USER_.USERNAME.eq(username));
    }

    @Override
    public boolean doesEmailExist(String email) {
        log.info("Check if email exists: {}", email);
        return fetchExist(Tables.USER_, Tables.USER_.EMAIL.eq(email));
    }
}
