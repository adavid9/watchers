package com.dawidantecki.watchers.exceptions;

public class RoleAlreadyExistsException extends RuntimeException {

    public RoleAlreadyExistsException(String message) {
        super(message);
    }

    public RoleAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
