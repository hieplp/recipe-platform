package com.hieplp.recipe.auth.domain.command.controller;

import com.hieplp.recipe.auth.domain.command.payload.request.forgot.ConfirmForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.GenerateForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.ResendForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ConfirmRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ResendRegisterOtpRequest;
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

    @PostMapping("/register/otp/resend")
    public CompletableFuture<CommonResponse> resendRegisterOtp(@RequestBody @Valid ResendRegisterOtpRequest request) {
        log.info("Resend OTP for register with request: {}", request);
        return authService.resendRegisterOtp(request);
    }

    @PostMapping("/forgot/otp")
    public CompletableFuture<CommonResponse> generateForgotOtp(@RequestBody @Valid GenerateForgotOtpRequest request) {
        log.info("Generate OTP for forgot password with request: {}", request);
        return authService.generateForgotOtp(request);
    }

    @PutMapping("/forgot/otp")
    public CompletableFuture<CommonResponse> confirmForgotOtp(@RequestBody @Valid ConfirmForgotOtpRequest request) {
        log.info("Confirm OTP for forgot password with request: {}", request);
        return authService.confirmForgotOtp(request);
    }

    @PostMapping("/forgot/otp/resend")
    public CompletableFuture<CommonResponse> resendForgotOtp(@RequestBody @Valid ResendForgotOtpRequest request) {
        log.info("Resend OTP for forgot password with request: {}", request);
        return authService.resendForgotOtp(request);
    }
}
