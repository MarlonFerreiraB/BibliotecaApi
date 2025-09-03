package io.github.marlon.bibliotecaapi.bibliotecaapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthorResponseDTO {
    private String id;
    private String name;

}
