package com.hieplp.recipe.auth.common.repository.impl;

import com.hieplp.recipe.auth.common.repository.OtpHistoryRepo;
import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static com.hieplp.recipe.auth.common.repository.generate.Tables.OTPHISTORY;

@Repository
@Slf4j
public class OtpHistoryRepoImpl extends BaseRepoImpl implements OtpHistoryRepo {
    public OtpHistoryRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public int countOtpHistory(String otpId, Byte type, LocalDate date) {
        log.info("Count otp history with otpId {}, type: {} and date {}", otpId, type, date);
        return context.fetchCount(OTPHISTORY, OTPHISTORY.OTPID.eq(otpId)
                .and(OTPHISTORY.TYPE.eq(type))
                .and(DSL.cast(OTPHISTORY.CREATEDAT, LocalDate.class).eq(date)));
    }
}
