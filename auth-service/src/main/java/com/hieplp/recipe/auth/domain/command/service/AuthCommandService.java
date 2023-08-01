package com.hieplp.recipe.auth.domain.command.service;


import com.hieplp.recipe.auth.domain.command.payload.request.forgot.ConfirmForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.GenerateForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.forgot.ResendForgotOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.login.LoginRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ConfirmRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.ResendRegisterOtpRequest;
import com.hieplp.recipe.common.payload.response.CommonResponse;

import java.util.concurrent.CompletableFuture;

public interface AuthCommandService {
    // -------------------------------------------------------------------------
    // XXX Register
    // -------------------------------------------------------------------------
    CompletableFuture<CommonResponse> generateRegisterOtp(GenerateRegisterOtpRequest request);

    CompletableFuture<CommonResponse> confirmRegisterOtp(ConfirmRegisterOtpRequest request);

    CompletableFuture<CommonResponse> resendRegisterOtp(ResendRegisterOtpRequest request);

    // -------------------------------------------------------------------------
    // XXX Forgot
    // -------------------------------------------------------------------------
    CompletableFuture<CommonResponse> generateForgotOtp(GenerateForgotOtpRequest request);

    CompletableFuture<CommonResponse> confirmForgotOtp(ConfirmForgotOtpRequest request);

    CompletableFuture<CommonResponse> resendForgotOtp(ResendForgotOtpRequest request);

    // -------------------------------------------------------------------------
    // XXX Login
    // -------------------------------------------------------------------------
    CommonResponse login(LoginRequest request);
}
