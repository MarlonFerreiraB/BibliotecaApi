package io.github.marlon.bibliotecaapi.bibliotecaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "author")
public class AuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank(message = "Este campo não pode ficar em branco.")
    @Size(max = 100, message = "Limite de caractere atingido.")
    private String name;
    @OneToMany(mappedBy = "authorModel")
    private List<BookModel> books;
}
