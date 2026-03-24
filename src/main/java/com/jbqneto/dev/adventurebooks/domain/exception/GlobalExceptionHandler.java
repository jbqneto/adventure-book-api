package com.jbqneto.dev.adventurebooks.domain.exception;

import com.jbqneto.dev.adventurebooks.api.dto.error.ApiErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public org.springframework.http.ResponseEntity<ApiErrorResponseDto> handleCategoryAlreadyExists(
            CategoryAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        var response = new ApiErrorResponseDto(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return org.springframework.http.ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public org.springframework.http.ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> fields = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fields.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", "Validation failed");
        body.put("path", request.getRequestURI());
        body.put("fields", fields);

        return org.springframework.http.ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public org.springframework.http.ResponseEntity<ApiErrorResponseDto> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto response = new ApiErrorResponseDto(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return org.springframework.http.ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public org.springframework.http.ResponseEntity<ApiErrorResponseDto> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        ApiErrorResponseDto response = new ApiErrorResponseDto(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Unexpected internal error",
                request.getRequestURI()
        );

        return org.springframework.http.ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
