package com.selman.exceptionhandling.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selman.exceptionhandling.exception.response.ErrorResponse;
import com.selman.exceptionhandling.exception.response.MyErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private final MessageSource apiErrorMessageSource;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        MyErrorResponse.MyErrorResponseBuilder baseBuilder = MyErrorResponse.builder();
        ErrorResponse.ErrorResponseBuilder errorResponseBuilder = ErrorResponse.builder();
        errorResponseBuilder.code("ERR-27");
        errorResponseBuilder.message("API_FORBIDDEN");

        baseBuilder.errorResponse(errorResponseBuilder.build());
        MyErrorResponse errorResponse = baseBuilder.build();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }

}

