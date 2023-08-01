package com.hieplp.recipe.auth.domain.query.projection;


import com.hieplp.recipe.auth.common.repository.TempUserRepo;
import com.hieplp.recipe.auth.domain.query.queries.tempuser.GetTempUserByOtpIdQuery;
import com.hieplp.recipe.common.entity.auth.TempUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TempUserProjection {

    private final TempUserRepo tempUserRepo;

    @QueryHandler
    private TempUserEntity handle(GetTempUserByOtpIdQuery query) {
        log.info("Get temp user by otpId: {}", query);
        var optionalTempUser = tempUserRepo.getTempUserByOtpId(query.getOtpId());
        log.info("Temp user: {}", optionalTempUser.get());
        return optionalTempUser.orElse(null);
    }
}
