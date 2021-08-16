package com.github.milomarten.bittwiddle.model.exception;

public class OverflowException extends RuntimeException {
    public OverflowException() {
        super();
    }

    public OverflowException(String message) {
        super(message);
    }

    public OverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverflowException(Throwable cause) {
        super(cause);
    }
}
