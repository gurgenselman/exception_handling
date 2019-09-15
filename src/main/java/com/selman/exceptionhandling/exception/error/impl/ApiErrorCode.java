package com.selman.exceptionhandling.exception.error.impl;

import com.selman.exceptionhandling.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ApiErrorCode implements ErrorCode {
    INTERNAL_ERROR              ("ERR-1", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED_ACCESS			("ERR-2", HttpStatus.UNAUTHORIZED),
    MISSING_ACCESS_TOKEN		("ERR-3", HttpStatus.UNAUTHORIZED),
    OBJECT_NOT_FOUND			("ERR-4", HttpStatus.BAD_REQUEST),
    NULL_ARGUMENT				("ERR-5", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT			("ERR-6", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_FILE_FORMAT		("ERR-7", HttpStatus.BAD_REQUEST),
    FILE_NOT_FOUND				("ERR-8", HttpStatus.BAD_REQUEST),
    INVALID_DATE				("ERR-9", HttpStatus.BAD_REQUEST),
    INVALID_FORMAT				("ERR-10", HttpStatus.BAD_REQUEST),
    HTTP_MESSAGE_NOT_READABLE	("ERR-11", HttpStatus.BAD_REQUEST),
    FILE_UPLOAD_FAILED			("ERR-12", HttpStatus.BAD_REQUEST),
    EMPTY_ANSWER_LIST			("ERR-13", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL				("ERR-14", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD			("ERR-15", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS		("ERR-16", HttpStatus.BAD_REQUEST),
    RESEND_CONFIRMATION			("ERR-17", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND				("ERR-18", HttpStatus.UNAUTHORIZED),
    INVALID_CAPTCHA				("ERR-19", HttpStatus.BAD_REQUEST),
    INVALID_ROLE				("ERR-20", HttpStatus.BAD_REQUEST),
    PASSWORDS_DONOT_MATCH		("ERR-21", HttpStatus.BAD_REQUEST),
    METHOD_NOT_ALLOWED          ("ERR-22", HttpStatus.METHOD_NOT_ALLOWED),
    MISSING_PARAMETER           ("ERR-23", HttpStatus.BAD_REQUEST),
    CAN_NOT_ADD_RECORD_BECAUSE_IT_EXISTS("ERR-24", HttpStatus.BAD_REQUEST),
    AUTH_TOKEN_NOT_FOUND	    ("ERR-25", HttpStatus.BAD_REQUEST),
    LOGIN_SDK_VALIDATION_EXCEPTION ("ERR-26", HttpStatus.UNAUTHORIZED),
    API_FORBIDDEN               ("ERR-27", HttpStatus.FORBIDDEN),
    TOKEN_INVALID_EXCEPTION     ("ERR-28", HttpStatus.UNAUTHORIZED),
    APPLICATION_NOT_FOUND_EXCEPTION     ("ERR-29", HttpStatus.UNAUTHORIZED),
    HASH_PARAM_MISSING_EXCEPTION ("ERR-30", HttpStatus.BAD_REQUEST),
    INVALID_HASH_EXCEPTION ("ERR-31", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_OR_DEVICE_NOT_FOUND_EXCEPTION("ERR-32", HttpStatus.UNAUTHORIZED),
    PACKAGE_NOT_FOUND_EXCEPTION("ERR-33", HttpStatus.BAD_REQUEST),
    SMS_CANNOT_SEND("ERR-34",HttpStatus.BAD_REQUEST),
    CLAIM_NOT_FOUND("ERR-35", HttpStatus.BAD_REQUEST),
    SERVICE_UNAVAILABLE("ERR-36", HttpStatus.SERVICE_UNAVAILABLE),
    STORE_VALIDATION_EXCEPTION("ERR-37", HttpStatus.BAD_REQUEST),
    USER_PROFILE_NOT_FOUND_EXCEPTION("ERR-38", HttpStatus.UNAUTHORIZED),
    PLATFORM_NOT_FOUND_EXCEPTION("ERR-39", HttpStatus.BAD_REQUEST),
    INCORRECT_PARAMETER_EXCEPTION("ERR-40", HttpStatus.BAD_REQUEST),
    CACHED_OBJECT_NOT_FOUND_EXCEPTION("ERR-41", HttpStatus.BAD_REQUEST),
    TOO_MANY_REQUESTS_EXCEPTION("ERR-42", HttpStatus.OK),
    USER_BLOCKED_EXCEPTION("ERR-43", HttpStatus.OK),
    INVALID_PARAMETER			("ERR-44", HttpStatus.BAD_REQUEST),;

    private final String code;
    private final HttpStatus httpStatus;

    public String code() { return code; }
    public HttpStatus httpStatus() {
        return httpStatus;
    }
}

