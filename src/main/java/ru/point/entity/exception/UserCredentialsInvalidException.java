package ru.point.entity.exception;

public class UserCredentialsInvalidException extends RuntimeException {
    public UserCredentialsInvalidException(String msg) {
        super(msg);
    }
}
