package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;

import io.github.marlon.bibliotecaapi.bibliotecaapi.enums.GeneroEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationDTO {
    @NotBlank(message = "Nao pode estar em branco")
    @Size(max = 100, message = "Limite de caractere atingido.")
    private String title;

    @NotNull(message = "nao pode estar em branco.")
    private GeneroEnum generoEnum;
    @NotNull(message = "Nao pode estar em branco.")
    @PastOrPresent
    private LocalDate publicationYear;
    @NotNull
    private boolean isAvailable;

    private String authorId;

}
