package com.hieplp.recipe.notification.common.repository.impl;

import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import com.hieplp.recipe.notification.common.repository.LogRepo;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class LogRepoImpl extends BaseRepoImpl implements LogRepo {
    public LogRepoImpl(DSLContext context) {
        super(context);
    }
}
