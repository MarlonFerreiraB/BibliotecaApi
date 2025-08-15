package io.github.marlon.bibliotecaapi.bibliotecaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookModel {
    private Integer actualyYear = LocalDate.now().getYear();
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank(message = "Nao pode estar em branco")
    @Size(max = 100, message = "Limite de caractere atingido.")
    private String title;
    @NotBlank(message = "Nao pode estar em branco")
    @Min(value = 1900)
    @Max(value = 2025)
    @PastOrPresent
    private Integer publicationYear;
    private boolean isAvailable;

}
