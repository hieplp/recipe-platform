package com.hieplp.recipe.auth.config.handler;

import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.exception.auth.ExceededOtpQuotaException;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class OtpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceededOtpQuotaException.class)
    public ResponseEntity<CommonResponse> handleExceededOtpQuotaException(ExceededOtpQuotaException e) {
        log.error("ExceededOtpQuotaException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.QUOTA_EXCEEDED), HttpStatus.OK);
    }

}
