package com.kaiwa.messageservice.controller;

import com.kaiwa.messageservice.payload.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<List<Map<String, String>>>> methodArgumentNotValidException(
            MethodArgumentNotValidException exception, HttpServletRequest request
    ) {
        List<Map<String, String>> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(errorValidation -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(errorValidation.getField(), errorValidation.getDefaultMessage());
                    return error;
                }).toList();

        return ResponseEntity.status(404).body(ErrorResponse.<List<Map<String, String>>>builder()
                .apiPath(request.getRequestURI())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(errors)
                .errorTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> globalException(Exception exception,
                                                                 HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.<String>builder()
                        .apiPath(request.getRequestURI())
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .errorMessage(exception.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<ErrorResponse<String>> responseStatusException(ResponseStatusException exception,
                                                                          HttpServletRequest request) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(ErrorResponse.<String>builder()
                        .apiPath(request.getRequestURI())
                        .errorCode(exception.getStatusCode().value())
                        .errorMessage(exception.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build());
    }

}
