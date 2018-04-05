package com.chothuesach.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HoaDonNotFoundException extends RuntimeException {

    private String message = "Hoa don not found";

    public HoaDonNotFoundException() {

    }

    public HoaDonNotFoundException(String message) {
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
