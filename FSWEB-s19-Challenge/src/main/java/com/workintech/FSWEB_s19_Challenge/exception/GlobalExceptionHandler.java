package com.workintech.FSWEB_s19_Challenge.exception;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private ErrorResponseCreator errorResponseCreator;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException ex){
        return errorResponseCreator.buildErrorResponse(ex, HttpStatus.NOT_FOUND, "Resource Not Found");
    }

    @ExceptionHandler(UnAuthorizedActionException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorized(UnAuthorizedActionException ex) {
        return errorResponseCreator.buildErrorResponse(ex, HttpStatus.FORBIDDEN, "Unauthorized Action");
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(ValidationException ex) {
        return errorResponseCreator.buildErrorResponse(ex, HttpStatus.BAD_REQUEST, "Validation Failed");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return errorResponseCreator.buildErrorResponse(new Exception(message), HttpStatus.BAD_REQUEST, "Validation Failed");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return errorResponseCreator.buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}
