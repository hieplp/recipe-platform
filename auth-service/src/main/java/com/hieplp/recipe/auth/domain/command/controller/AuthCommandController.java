package com.hieplp.recipe.auth.domain.command.controller;

import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.service.AuthCommandService;
import com.hieplp.recipe.common.enums.response.SuccessCode;
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
    public CommonResponse verifyRegisterOtp(@RequestBody @Valid VerifyRegisterOtpRequest request) {
        log.info("Verify OTP for register with request: {}", request);
        var data = authService.verifyRegisterOtp(request);
        return new CommonResponse(SuccessCode.SUCCESS, data);
    }

    @PostMapping("/register")
    public CommonResponse register(@RequestBody RegisterRequest request) {
        log.info("Register with request: {}", request);
        var data = authService.register(request);
        return new CommonResponse(SuccessCode.SUCCESS, data);
    }
}
