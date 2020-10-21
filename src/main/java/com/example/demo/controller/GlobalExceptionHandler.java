package com.example.demo.controller;

import com.example.demo.selfexception.ErrorResult;
import com.example.demo.selfexception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResult> handle(UserNotFoundException ex) {
        String[] errorWithStatus = HttpStatus.NOT_FOUND.toString().split(" ");
        ErrorResult errorResult = new ErrorResult(new Date().toString(),errorWithStatus[1], errorWithStatus[0], ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        String[] errorWithStatus = HttpStatus.NOT_FOUND.toString().split(" ");
        ErrorResult errorResult = new ErrorResult(new Date().toString(),errorWithStatus[1], errorWithStatus[0], message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        String message = "";
        for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
            message = constraint.getMessage();
            break;
        }
        String[] errorWithStatus = HttpStatus.NOT_FOUND.toString().split(" ");
        ErrorResult errorResult = new ErrorResult(new Date().toString(),errorWithStatus[1], errorWithStatus[0], message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
