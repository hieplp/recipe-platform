package com.hieplp.recipe.auth.common.repository;

import com.hieplp.recipe.common.jooq.base.BaseRepo;

import java.time.LocalDate;

public interface OtpHistoryRepo extends BaseRepo {
    int countOtpHistory(String otpId, Byte type, LocalDate date);
}
