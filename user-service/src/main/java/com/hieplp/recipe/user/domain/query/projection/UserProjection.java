package com.hieplp.recipe.user.domain.query.projection;

import com.hieplp.recipe.common.entity.user.UserEntity;
import com.hieplp.recipe.common.query.queries.user.*;
import com.hieplp.recipe.user.common.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserProjection {

    private final UserRepo userRepo;

    @QueryHandler
    private boolean handle(CheckUsernameExistenceQuery query) {
        log.info("Check if username exists: {}", query);
        return userRepo.doesEmailExist(query.getUsername());
    }

    @QueryHandler
    private boolean handle(CheckEmailExistenceQuery query) {
        log.info("Check if email exists: {}", query);
        return userRepo.doesEmailExist(query.getEmail());
    }

    @QueryHandler
    private String handle(GetUserIdByEmailQuery query) {
        log.info("Get user id by email: {}", query);
        return userRepo.getUserIdByEmail(query.getEmail()).orElse(null);
    }

    @QueryHandler
    private UserEntity handle(GetUserByUsernameQuery query) {
        log.info("Get user by username: {}", query);
        return userRepo.getUserByUsername(query.getUsername()).orElse(null);
    }

    @QueryHandler
    private UserEntity handle(GetUserByUserIdQuery query) {
        log.info("Get user by userId: {}", query);
        return userRepo.getUserByUserId(query.getUserId()).orElse(null);
    }
}
