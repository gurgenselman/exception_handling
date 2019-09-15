package com.selman.exceptionhandling.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Video {

    @NotNull
    String fileName;

    @NotNull
    String id;
}
