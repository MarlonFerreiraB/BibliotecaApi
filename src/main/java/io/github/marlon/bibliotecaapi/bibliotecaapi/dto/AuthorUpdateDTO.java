package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorUpdateDTO(
        @NotBlank(message = "Este campo não pode ficar em branco.")
        @Size(max = 100, message = "Limite de caractere atingido.")
        String name
) {}
