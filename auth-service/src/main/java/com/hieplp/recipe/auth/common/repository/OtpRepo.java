package com.hieplp.recipe.auth.common.repository;

import com.hieplp.recipe.common.entity.auth.OtpEntity;
import com.hieplp.recipe.common.jooq.base.BaseRepo;

import java.time.LocalDate;
import java.util.Optional;

public interface OtpRepo extends BaseRepo {
    int countOtp(String sendTo, Byte type, LocalDate date);

    Optional<OtpEntity> getOtp(String otpId);
}
