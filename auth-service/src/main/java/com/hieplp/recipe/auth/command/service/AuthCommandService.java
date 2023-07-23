package com.hieplp.recipe.auth.command.service;


import com.hieplp.recipe.auth.command.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.command.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.command.payload.response.auth.GenerateRegisterOtpResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.command.payload.response.auth.VerifyRegisterOtpResponse;

public interface AuthCommandService {
    // -------------------------------------------------------------------------
    // XXX Register
    // -------------------------------------------------------------------------
    GenerateRegisterOtpResponse generateRegisterOtp(GenerateRegisterOtpRequest request);

    VerifyRegisterOtpResponse verifyRegisterOtp(VerifyRegisterOtpRequest request);

    RegisterResponse register(RegisterRequest request);
}
