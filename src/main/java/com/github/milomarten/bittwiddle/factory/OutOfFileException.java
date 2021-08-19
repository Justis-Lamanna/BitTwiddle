package com.github.milomarten.bittwiddle.factory;

public class OutOfFileException extends RuntimeException {
    public OutOfFileException() {
        super();
    }

    public OutOfFileException(String message) {
        super(message);
    }

    public OutOfFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfFileException(Throwable cause) {
        super(cause);
    }
}
