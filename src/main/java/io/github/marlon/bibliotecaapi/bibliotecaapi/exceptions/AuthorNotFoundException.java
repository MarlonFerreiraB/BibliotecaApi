package io.github.marlon.bibliotecaapi.bibliotecaapi.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
