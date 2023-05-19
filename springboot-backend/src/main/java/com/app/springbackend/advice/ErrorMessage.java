package com.app.springbackend.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private Timestamp timestamp;
    private String message, description;
}
