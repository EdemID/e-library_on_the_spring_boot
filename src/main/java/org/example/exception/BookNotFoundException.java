package org.example.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(int id) {
        super(String.format("Book with id=%s not found", id));
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
