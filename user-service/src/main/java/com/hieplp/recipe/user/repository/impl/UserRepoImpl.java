package com.hieplp.recipe.user.repository.impl;

import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import com.hieplp.recipe.user.repository.UserRepo;
import com.hieplp.recipe.user.repository.generate.tables.records.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.hieplp.recipe.user.repository.generate.Tables.USER_;

@Slf4j
@Repository
public class UserRepoImpl extends BaseRepoImpl implements UserRepo {
    public UserRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public UserRecord getUserRecord(String userId) {
        log.info("Get user record by userId {}", userId);
        return fetchOneNotNull(USER_, USER_.USERID.eq(userId));
    }
}
