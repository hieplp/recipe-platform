package com.hieplp.recipe.auth.common.repository.impl;

import com.hieplp.recipe.auth.common.repository.TempUserRepo;
import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class TempUserRepoImpl extends BaseRepoImpl implements TempUserRepo {
    public TempUserRepoImpl(DSLContext context) {
        super(context);
    }
}
