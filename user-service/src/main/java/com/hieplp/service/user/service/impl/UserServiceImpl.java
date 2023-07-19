package com.hieplp.service.user.service.impl;

import com.hieplp.service.user.repository.UserRepo;
import com.hieplp.service.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public String getUserByUserId() {
        log.info("Get user by userId");
        var userId = userRepo.getUserRecord("1").getUserid();
        return userId;
    }
}
