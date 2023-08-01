package com.hieplp.recipe.user.domain.query.projection;

import com.hieplp.recipe.common.entity.user.PasswordEntity;
import com.hieplp.recipe.common.query.queries.password.GetPasswordByUserIdQuery;
import com.hieplp.recipe.user.common.repository.PasswordRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordProjection {

    private final PasswordRepo passwordRepo;

    @QueryHandler
    private PasswordEntity handle(GetPasswordByUserIdQuery query) {
        log.info("Handling query: {}", query);
        return passwordRepo.getPasswordByUserId(query.getUserId()).orElse(null);
    }
}
