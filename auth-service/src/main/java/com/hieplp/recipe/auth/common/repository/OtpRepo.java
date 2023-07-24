package com.hieplp.recipe.auth.common.repository;

import com.hieplp.recipe.common.jooq.base.BaseRepo;

import java.time.LocalDate;

public interface OtpRepo extends BaseRepo {
    int countOtp(String sendTo, Byte type, LocalDate date);
}
