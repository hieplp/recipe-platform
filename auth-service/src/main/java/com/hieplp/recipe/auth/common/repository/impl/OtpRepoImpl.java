package com.hieplp.recipe.auth.common.repository.impl;

import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.common.repository.generate.Tables;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Slf4j
public class OtpRepoImpl extends BaseRepoImpl implements OtpRepo {
    public OtpRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public int countOtp(String sendTo, Byte type, LocalDate date) {
        log.info("Count otp with sendTo {}, type: {} and date {}", sendTo, type, date);
        return context.fetchCount(Tables.OTP, Tables.OTP.SENDTO.eq(sendTo)
                .and(Tables.OTP.STATUS.ne(OtpStatus.CANCELED.getStatus()))
                .and(DSL.cast(Tables.OTP.CREATEDAT, LocalDate.class).eq(date)));
    }

    @Override
    public Optional<OtpEntity> getOtp(String otpId) {
        return fetchOne(Tables.OTP, Tables.OTP.OTPID.eq(otpId), OtpEntity.class);
    }
}
