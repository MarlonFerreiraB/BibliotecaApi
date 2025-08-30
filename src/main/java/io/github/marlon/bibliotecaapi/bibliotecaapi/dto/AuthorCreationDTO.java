package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class AuthorCreationDTO {

    @NotBlank(message = "Este campo não pode ficar em branco.")
    @Size(max = 100, message = "Limite de caractere atingido.")
    private String name;
}
