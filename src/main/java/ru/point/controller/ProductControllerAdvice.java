package ru.point.controller;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.point.entity.exception.ExceptionResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalIdentifierException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalIdentifierException(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
