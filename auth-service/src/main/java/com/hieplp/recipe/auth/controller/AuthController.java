package com.hieplp.recipe.auth.controller;

import com.hieplp.recipe.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{username}")
    public boolean doesUsernameExist(@PathVariable String username) {
        log.info("Check if username {} exists", username);
        return authService.doesUsernameExist(username);
    }
}
