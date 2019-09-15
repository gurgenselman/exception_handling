package com.selman.exceptionhandling.exception.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String code();
    HttpStatus httpStatus();
}
