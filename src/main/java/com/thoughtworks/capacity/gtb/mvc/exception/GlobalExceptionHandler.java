package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResult handle(ResponseStatusException ex) {
        return new ErrorResult(ex.getStatus().toString(), ex.getReason());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handle(MethodArgumentNotValidException ex) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return new ErrorResult(HttpStatus.BAD_REQUEST.toString(), message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResult handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = "";
        for (ConstraintViolation<?> constraint : violations) {
            message = constraint.getMessage();
            break;
        }
        return new ErrorResult(HttpStatus.BAD_REQUEST.toString(), message);
    }
}
