package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;

import io.github.marlon.bibliotecaapi.bibliotecaapi.enums.GeneroEnum;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookUpdateDTO(
        @Size(max = 100, message = "Limite de caractere atingido.")
        String title,
        GeneroEnum generoEnum,
        @PastOrPresent
        LocalDate publicationYear,
        boolean isAvailable,
        String authorId
){}
