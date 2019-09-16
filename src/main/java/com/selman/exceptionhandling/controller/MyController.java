package com.selman.exceptionhandling.controller;

import com.selman.exceptionhandling.exception.error.impl.ApiErrorCode;
import com.selman.exceptionhandling.exception.type.BaseRuntimeException;
import com.selman.exceptionhandling.exception.type.IncorrectParameterException;
import com.selman.exceptionhandling.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class MyController {


    @GetMapping("/init-medianova")
    public String initVideo(@Valid Video video) throws Exception {
        String[] a2 = {"1903"};
        throw  new IncorrectParameterException("lol");
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('GUEST')")
    public String initVideo2(@Valid Video video) throws Exception {
        String[] a2 = {"1903"};
        throw  new IncorrectParameterException("lol");
    }
}
