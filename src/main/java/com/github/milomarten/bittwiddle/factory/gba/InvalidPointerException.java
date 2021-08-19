package com.github.milomarten.bittwiddle.factory.gba;

public class InvalidPointerException extends RuntimeException {
    public InvalidPointerException() {
        super();
    }

    public InvalidPointerException(String message) {
        super(message);
    }

    public InvalidPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPointerException(Throwable cause) {
        super(cause);
    }
}
