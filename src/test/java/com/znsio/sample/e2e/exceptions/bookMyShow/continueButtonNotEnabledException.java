package com.znsio.sample.e2e.exceptions.bookMyShow;

public class continueButtonNotEnabledException extends Throwable {
    public continueButtonNotEnabledException(String message) {
        super(message);
    }

    public continueButtonNotEnabledException(String message, Exception e) {
        super(message, e);
    }

}
