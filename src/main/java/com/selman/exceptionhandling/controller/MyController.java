package com.selman.exceptionhandling.controller;

import com.selman.exceptionhandling.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class MyController {


    @GetMapping("/init-medianova")
    public String initVideo(Video video){

        return "OK2";
    }
}
