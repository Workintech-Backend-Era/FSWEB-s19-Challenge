package com.workintech.FSWEB_s19_Challenge.exception;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseCreator {

    public ResponseEntity<Map<String, Object>> buildErrorResponse(Exception ex, HttpStatus httpStatus, String error){
        Map<String, Object> body = new HashMap<>();
        body.put("error", error);
        body.put("message",ex.getMessage());
        body.put("status", httpStatus.value());
        body.put("timeStamp", ZonedDateTime.now());
        return new ResponseEntity<>(body, httpStatus);
    }

}
