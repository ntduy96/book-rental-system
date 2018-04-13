package com.chothuesach.exception;

public class ResourceConflictException extends RuntimeException {

    private String message = "Resource already exists";

    public ResourceConflictException() {

    }

    public ResourceConflictException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
