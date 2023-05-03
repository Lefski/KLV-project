package com.app.springbackend.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String message, description;
}
