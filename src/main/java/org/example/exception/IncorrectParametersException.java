package org.example.exception;

public class IncorrectParametersException extends RuntimeException {

    public IncorrectParametersException(final String message) {
        super(message);
    }
}
