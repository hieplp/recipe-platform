package com.hieplp.recipe.auth.domain.command.service;


import com.hieplp.recipe.auth.domain.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.domain.command.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.domain.command.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.domain.command.payload.response.auth.VerifyRegisterOtpResponse;
import com.hieplp.recipe.common.payload.response.CommonResponse;

import java.util.concurrent.CompletableFuture;

public interface AuthCommandService {
    // -------------------------------------------------------------------------
    // XXX Register
    // -------------------------------------------------------------------------
    CompletableFuture<CommonResponse> generateRegisterOtp(GenerateRegisterOtpRequest request);

    VerifyRegisterOtpResponse verifyRegisterOtp(VerifyRegisterOtpRequest request);

    RegisterResponse register(RegisterRequest request);
}
