package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;

import io.github.marlon.bibliotecaapi.bibliotecaapi.enums.GeneroEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookUpdateDTO(
        @NotBlank(message = "Nao pode estar em branco")
        @Size(max = 100, message = "Limite de caractere atingido.")
        String title,
        @NotNull(message="nao pode estar em branco.")
        GeneroEnum generoEnum,
        @NotNull(message = "Nao pode estar em branco.")
        @PastOrPresent
        LocalDate publicationYear,
        @NotNull
        boolean isAvailable,
        @NotNull(message = "id esta nulo")
        String authorId
){}
