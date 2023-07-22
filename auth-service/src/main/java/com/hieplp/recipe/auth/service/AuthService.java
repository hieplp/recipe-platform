package com.hieplp.recipe.auth.service;

import com.hieplp.recipe.auth.payload.request.register.GenerateRegisterOtpRequest;
import com.hieplp.recipe.auth.payload.request.register.RegisterRequest;
import com.hieplp.recipe.auth.payload.request.register.VerifyRegisterOtpRequest;
import com.hieplp.recipe.auth.payload.response.auth.RegisterResponse;
import com.hieplp.recipe.auth.payload.response.auth.VerifyRegisterOtpResponse;

public interface AuthService {
    boolean doesUsernameExist(String username);

    // -------------------------------------------------------------------------
    // XXX Register
    // -------------------------------------------------------------------------
    GenerateRegisterOtpRequest generateRegisterOtp(GenerateRegisterOtpRequest request);

    VerifyRegisterOtpResponse verifyRegisterOtp(VerifyRegisterOtpRequest request);

    RegisterResponse register(RegisterRequest request);
}
