package com.example.userservice.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildResponse(RuntimeException ex, HttpStatus httpStatus, String errorName, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(), httpStatus.value(), errorName, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request){
        return buildResponse(ex, HttpStatus.BAD_REQUEST, "Bad Request", request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest request){
        return buildResponse(ex, HttpStatus.NOT_FOUND, "Not Found", request);
    }


}
