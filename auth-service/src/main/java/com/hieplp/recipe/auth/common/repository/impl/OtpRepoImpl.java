package com.hieplp.recipe.auth.common.repository.impl;

import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.common.entity.auth.OtpEntity;
import com.hieplp.recipe.common.enums.otp.OtpStatus;
import com.hieplp.recipe.common.jooq.base.BaseRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import static com.hieplp.recipe.auth.common.repository.generate.Tables.OTP;

@Repository
@Slf4j
public class OtpRepoImpl extends BaseRepoImpl implements OtpRepo {
    public OtpRepoImpl(DSLContext context) {
        super(context);
    }

    @Override
    public int countOtp(String sendTo, Byte type, LocalDate date) {
        log.info("Count otp with sendTo {}, type: {} and date {}", sendTo, type, date);
        return context.fetchCount(OTP, OTP.SENDTO.eq(sendTo)
                .and(OTP.STATUS.ne(OtpStatus.CANCELED.getStatus()))
                .and(OTP.TYPE.eq(type))
                .and(DSL.cast(OTP.CREATEDAT, LocalDate.class).eq(date)));
    }

    @Override
    public Optional<OtpEntity> getOtp(String otpId) {
        return fetchOne(OTP, OTP.OTPID.eq(otpId), OtpEntity.class);
    }
}
