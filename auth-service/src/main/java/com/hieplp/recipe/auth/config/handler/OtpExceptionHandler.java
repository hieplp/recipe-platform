package com.hieplp.recipe.auth.config.handler;

import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.exception.auth.ExceededOtpQuotaException;
import com.hieplp.recipe.common.exception.auth.ExpiredOtpException;
import com.hieplp.recipe.common.exception.auth.IssuedOtpException;
import com.hieplp.recipe.common.exception.auth.WrongOtpException;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class OtpExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExceededOtpQuotaException.class)
    public ResponseEntity<CommonResponse> handleExceededOtpQuotaException(ExceededOtpQuotaException e) {
        log.error("ExceededOtpQuotaException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.QUOTA_EXCEEDED), HttpStatus.OK);
    }

    @ExceptionHandler(IssuedOtpException.class)
    public ResponseEntity<CommonResponse> handleIssuedOtpException(IssuedOtpException e) {
        log.error("IssuedOtpException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.ISSUED_OTP), HttpStatus.OK);
    }

    @ExceptionHandler(ExpiredOtpException.class)
    public ResponseEntity<CommonResponse> handleExpiredOtpException(ExpiredOtpException e) {
        log.error("ExpiredOtpException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.EXPIRED_OTP), HttpStatus.OK);
    }

    @ExceptionHandler(WrongOtpException.class)
    public ResponseEntity<CommonResponse> handleWrongOtpException(WrongOtpException e) {
        log.error("WrongOtpException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.WRONG_OTP), HttpStatus.OK);
    }
}
