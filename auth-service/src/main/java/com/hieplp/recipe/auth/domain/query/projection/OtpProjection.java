package com.hieplp.recipe.auth.domain.query.projection;

import com.hieplp.recipe.auth.common.entity.OtpEntity;
import com.hieplp.recipe.auth.common.repository.OtpRepo;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetOtpQuery;
import com.hieplp.recipe.auth.domain.query.queries.otp.GetTodayOtpQuotaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class OtpProjection {

    private final OtpRepo otpRepo;

    @QueryHandler
    private int handle(GetTodayOtpQuotaQuery query) {
        log.info("Count today otp quota: {}", query);
        return otpRepo.countOtp(query.getSendTo(), query.getType(), LocalDate.now());
    }

    @QueryHandler
    private OtpEntity handle(GetOtpQuery query) {
        log.info("Get otp: {}", query);
        var optionalOtp = otpRepo.getOtp(query.getOtpId());
        return optionalOtp.orElse(null);
    }
}
