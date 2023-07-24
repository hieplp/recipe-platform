package com.hieplp.recipe.auth.query.projection;

import com.hieplp.recipe.auth.query.queries.GetTodayOtpQuotaQuery;
import com.hieplp.recipe.auth.common.repository.OtpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class OtpProjection {

    private final OtpRepo otpRepo;

    @QueryHandler
    private int handle(GetTodayOtpQuotaQuery query) {
        log.info("Count today otp quota: {}", query);
        return otpRepo.countOtp(query.getSendTo(), query.getType(), LocalDate.now());
    }
}
