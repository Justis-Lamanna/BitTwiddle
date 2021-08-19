package com.github.milomarten.bittwiddle.factory;

public class NoSuchFileException extends RuntimeException {
    public NoSuchFileException() {
        super();
    }

    public NoSuchFileException(String message) {
        super(message);
    }

    public NoSuchFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchFileException(Throwable cause) {
        super(cause);
    }
}
