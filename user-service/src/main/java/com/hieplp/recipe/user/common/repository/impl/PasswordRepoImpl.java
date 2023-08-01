package com.hieplp.recipe.user.common.repository.impl;

import com.hieplp.recipe.common.entity.user.PasswordEntity;
import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import com.hieplp.recipe.user.common.repository.PasswordRepo;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.hieplp.recipe.user.common.repository.generate.Tables.PASSWORD;

@Slf4j
@Repository
public class PasswordRepoImpl extends BaseRepoImpl implements PasswordRepo {
    public PasswordRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public Optional<PasswordEntity> getPasswordByUserId(String userId) {
        log.info("Getting password by userId: {}", userId);
        return fetchOne(PASSWORD, PASSWORD.USERID.eq(userId), PasswordEntity.class);
    }
}
