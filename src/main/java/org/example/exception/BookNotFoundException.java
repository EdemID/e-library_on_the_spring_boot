package org.example.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(final int id) {
        super(String.format("Book with id=%s not found", id));
    }

    public BookNotFoundException(final String message) {
        super(message);
    }
}
