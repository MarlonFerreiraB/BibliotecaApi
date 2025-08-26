package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDTO {
    private String id;
    @NotBlank(message = "Este campo não pode ficar em branco.")
    @Size(max = 100, message = "Limite de caractere atingido.")
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<BookResponseDTO> books;
}
