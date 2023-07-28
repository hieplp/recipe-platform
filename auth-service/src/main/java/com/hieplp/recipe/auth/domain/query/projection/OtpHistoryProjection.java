package com.hieplp.recipe.auth.domain.query.projection;

import com.hieplp.recipe.auth.common.repository.OtpHistoryRepo;
import com.hieplp.recipe.auth.domain.query.queries.history.GetTodayOtpHistoryQuotaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class OtpHistoryProjection {

    private final OtpHistoryRepo otpHistoryRepo;

    @QueryHandler
    private int handle(GetTodayOtpHistoryQuotaQuery query) {
        log.info("Count today otp history quota: {}", query);
        return otpHistoryRepo.countOtpHistory(query.getOtpId(), query.getType(), LocalDate.now());
    }
}
