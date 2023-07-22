package com.hieplp.recipe.auth.controller;

import com.hieplp.recipe.auth.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.service.AuthService;
import com.hieplp.recipe.common.enums.response.SuccessCode;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/register/otp")
    public CommonResponse generateRegisterOtp(@RequestBody @Valid GenerateRegisterOtpRequest request) {
        log.info("Generate OTP for register with request: {}", request);
        var data = authService.generateRegisterOtp(request);
        return new CommonResponse(SuccessCode.SUCCESS, data);
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
