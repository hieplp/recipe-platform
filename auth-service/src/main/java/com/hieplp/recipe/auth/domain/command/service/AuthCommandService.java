package com.hieplp.recipe.auth.domain.command.service;


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

}
