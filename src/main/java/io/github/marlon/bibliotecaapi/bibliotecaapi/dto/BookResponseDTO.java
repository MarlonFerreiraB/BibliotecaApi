package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;

import io.github.marlon.bibliotecaapi.bibliotecaapi.enums.GeneroEnum;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BookResponseDTO {
    private String id;
    private String title;
    private GeneroEnum generoEnum;
    private LocalDate publicationYear;
    private boolean isAvailable;

    private AuthorResponseDTO authorResponseDTO;
}
