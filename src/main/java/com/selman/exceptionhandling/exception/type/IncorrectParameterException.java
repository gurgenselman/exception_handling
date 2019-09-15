package com.selman.exceptionhandling.exception.type;

import com.selman.exceptionhandling.exception.error.impl.ApiErrorCode;

import java.util.Arrays;

public class IncorrectParameterException extends BaseRuntimeException {
    public IncorrectParameterException(String param) {
        super(ApiErrorCode.INCORRECT_PARAMETER_EXCEPTION, String.format("Incorrect Parameter: %s", param));
    }

    public IncorrectParameterException(String param, String... parameterName) {
        super(ApiErrorCode.INCORRECT_PARAMETER_EXCEPTION, String.format("Incorrect Parameter: %s, It Could be, %s", param, Arrays.toString(parameterName)));
    }
}
