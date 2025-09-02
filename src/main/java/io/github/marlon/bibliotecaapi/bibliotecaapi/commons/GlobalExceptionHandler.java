package io.github.marlon.bibliotecaapi.bibliotecaapi.commons;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.ErrorResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.exceptions.AuthorNotFoundException;
import io.github.marlon.bibliotecaapi.bibliotecaapi.exceptions.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundAuthor(AuthorNotFoundException ex){
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundBook(BookNotFoundException ex){
        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
