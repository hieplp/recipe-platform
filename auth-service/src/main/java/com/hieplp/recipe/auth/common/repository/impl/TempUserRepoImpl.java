package com.hieplp.recipe.auth.common.repository.impl;

import com.hieplp.recipe.auth.common.repository.TempUserRepo;
import com.hieplp.recipe.common.entity.auth.TempUserEntity;
import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.hieplp.recipe.auth.common.repository.generate.Tables.TEMP_USER;

@Repository
@Slf4j
public class TempUserRepoImpl extends BaseRepoImpl implements TempUserRepo {
    public TempUserRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public Optional<TempUserEntity> getTempUserByOtpId(String otpId) {
        log.info("Get temp user with otpId: {}", otpId);
        return fetchOne(TEMP_USER, TEMP_USER.OTPID.eq(otpId), TempUserEntity.class);
    }
}
