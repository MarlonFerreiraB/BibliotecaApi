package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AuthorDTO {

    @NotBlank(message = "Este campo não pode ficar em branco.")
    @Size(max = 100, message = "Limite de caractere atingido.")
    private String name;
    private List<BookModel> books;
}
