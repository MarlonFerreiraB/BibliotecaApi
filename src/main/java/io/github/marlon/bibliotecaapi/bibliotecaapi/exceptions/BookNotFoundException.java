package io.github.marlon.bibliotecaapi.bibliotecaapi.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
