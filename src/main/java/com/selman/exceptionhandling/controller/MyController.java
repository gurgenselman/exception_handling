package com.selman.exceptionhandling.controller;

import com.selman.exceptionhandling.exception.type.IncorrectParameterException;
import com.selman.exceptionhandling.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class MyController {


    @GetMapping("/init-medianova")
    public String initVideo2(@Valid Video video)  {
        String[] a2 = {"1903"};
        throw new IncorrectParameterException("selman",a2);

        //return "OK2";
    }
}
