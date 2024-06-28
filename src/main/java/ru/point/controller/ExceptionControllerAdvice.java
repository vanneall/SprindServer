package ru.point.controller;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.point.entity.exception.ExceptionResponse;
import ru.point.entity.exception.UserAlreadyExistException;
import ru.point.entity.exception.UserCredentialsInvalidException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalIdentifierException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalIdentifierException(IllegalIdentifierException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserCredentialsInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleUserCredentialsInvalidException(UserCredentialsInvalidException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), LocalDateTime.now()), HttpStatus.FORBIDDEN);
    }
}
