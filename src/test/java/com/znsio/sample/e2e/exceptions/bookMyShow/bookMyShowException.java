package com.znsio.sample.e2e.exceptions.bookMyShow;

public class bookMyShowException extends RuntimeException {
    public bookMyShowException(String message) {
        super(message);
    }

    public bookMyShowException(String message, Exception e) {
        super(message, e);
    }
}
