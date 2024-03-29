package com.selman.exceptionhandling.exception.config;

import com.selman.exceptionhandling.exception.error.ErrorCode;
import com.selman.exceptionhandling.exception.error.impl.ApiErrorCode;
import com.selman.exceptionhandling.exception.type.BaseRuntimeException;
import com.selman.exceptionhandling.exception.response.ErrorResponse;
import com.selman.exceptionhandling.exception.response.MyErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomGlobalExceptionHandler {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";
    private final MessageSource apiErrorMessageSource;

    @ExceptionHandler(BindException.class)
    ResponseEntity<MyErrorResponse> handleBindException(Exception exception, Locale locale, HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        log.error("Exception occured: request-uri:[{}], class: [{}], msg: [{}] \n{}", httpServletRequest.getRequestURI(), exception.getClass().getName(), exception.getMessage(), extractStackTrace(exception));
        return ResponseEntity.status(ApiErrorCode.INCORRECT_PARAMETER_EXCEPTION.httpStatus()).body(getInvalidParameterErrorResponse(bindingResult));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<MyErrorResponse> handleException(Exception exception, Locale locale, HttpServletRequest httpServletRequest) {
        log.error("Exception occured: request-uri:[{}], class: [{}], msg: [{}] \n{}", httpServletRequest.getRequestURI(), exception.getClass().getName(), exception.getMessage(), extractStackTrace(exception));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getApiBaseErrorResponse(exception));
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<MyErrorResponse> handleAccessDeniedException(Exception exception, Locale locale, HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getApiBaseErrorResponse(exception));
    }

    @ExceptionHandler(BaseRuntimeException.class)
    ResponseEntity<MyErrorResponse> handleServiceExceptions2(BaseRuntimeException exception, Locale locale, HttpServletRequest httpServletRequest) {
        ErrorCode errorCode = exception.getErrorCode();

//        boolean ignoreErrorLog = (exception instanceof ApiAccessDeniedException) || (exception instanceof ClaimNotFoundException);
        boolean ignoreErrorLog = false;

        if (! ignoreErrorLog) {
            log.error("Exception occured: request-uri:[{}], class: [{}], msg: [{}] \n{}", httpServletRequest.getRequestURI(), exception.getClass().getName(), exception.getMessage(), extractStackTrace(exception));
        }

        return ResponseEntity.status(errorCode.httpStatus()).body(getApiBaseErrorResponse(exception));
    }

    private MyErrorResponse getInvalidParameterErrorResponse(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<String> messages = new ArrayList<>();
        String preMessage = "Validation is failed. ";

        for (FieldError e : errors){
            messages.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
        }

        return MyErrorResponse.builder().errorResponse(ErrorResponse.builder().code(ApiErrorCode.INVALID_PARAMETER.code()).message(preMessage + messages.toString()).build()).build();
    }

    private MyErrorResponse getApiBaseErrorResponse(Exception ex) {
        Optional<ErrorCode> maybeErrorCode = Optional.empty();
        Optional<Object[]> maybeParams = Optional.empty();
        Optional<String> maybeMessage = Optional.empty();

        if (ex instanceof AuthenticationException) {
            maybeErrorCode = Optional.of(ApiErrorCode.UNAUTHORIZED_ACCESS);
        } else if (ex instanceof BaseRuntimeException) {
            maybeErrorCode = Optional.ofNullable(((BaseRuntimeException) ex).getErrorCode());
            maybeParams = Optional.ofNullable(((BaseRuntimeException) ex).getParams());
        }
//        else if (ex instanceof DataIntegrityViolationException) {
//            maybeErrorCode = Optional.of(ApiErrorCode.CAN_NOT_ADD_RECORD_BECAUSE_IT_EXISTS);
//        }
        else if (ex instanceof AccessDeniedException) {
            maybeErrorCode = Optional.of(ApiErrorCode.API_FORBIDDEN);
        } else {
            maybeErrorCode = Optional.of(ApiErrorCode.INTERNAL_ERROR);
            log.error("Api Error occurred. {}", ExceptionUtils.getRootCauseMessage(ex));
        }

        MyErrorResponse.MyErrorResponseBuilder baseBuilder = MyErrorResponse.builder();

        ErrorResponse.ErrorResponseBuilder errorResponseBuilder = ErrorResponse.builder();
        errorResponseBuilder.message(NO_MESSAGE_AVAILABLE);

        if (maybeErrorCode.isPresent()) {
            maybeMessage = Optional.ofNullable(apiErrorMessageSource.getMessage(maybeErrorCode.get().code(), maybeParams.orElse(null), LocaleContextHolder.getLocale()));
            errorResponseBuilder.code(maybeErrorCode.get().code());
        }
        if (maybeMessage.isPresent())
            errorResponseBuilder.message(maybeMessage.get());

        baseBuilder.errorResponse(errorResponseBuilder.build());

        return baseBuilder.build();
    }


    public static String extractStackTrace(Exception ex) {
        StringBuffer sb = new StringBuffer();
        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            if (stackTraceElement.getClassName().contains("com.selman"))
                sb.append("\tat ").append(stackTraceElement.toString()).append("\n");
        }

        return sb.toString();
    }

}

