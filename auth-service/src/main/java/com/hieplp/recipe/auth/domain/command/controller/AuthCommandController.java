package com.hieplp.recipe.auth.domain.command.controller;

import com.hieplp.recipe.auth.domain.command.payload.request.register.ConfirmRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.service.AuthCommandService;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthCommandController {

    private final AuthCommandService authService;

    @PostMapping("/register/otp")
    public CompletableFuture<CommonResponse> generateRegisterOtp(@RequestBody @Valid GenerateRegisterOtpRequest request) {
        log.info("Generate OTP for register with request: {}", request);
        return authService.generateRegisterOtp(request);
    }

    @PutMapping("/register/otp")
    public CompletableFuture<CommonResponse> confirmRegisterOtp(@RequestBody @Valid ConfirmRegisterOtpRequest request) {
        log.info("Confirm OTP for register with request: {}", request);
        return authService.confirmRegisterOtp(request);
    }
}
