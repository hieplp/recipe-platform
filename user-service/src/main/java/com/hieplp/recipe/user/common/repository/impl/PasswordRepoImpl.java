package com.hieplp.recipe.user.common.repository.impl;

import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import com.hieplp.recipe.user.common.repository.PasswordRepo;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PasswordRepoImpl extends BaseRepoImpl implements PasswordRepo {
    public PasswordRepoImpl(DSLContext context) {
        super(context);
    }
}
