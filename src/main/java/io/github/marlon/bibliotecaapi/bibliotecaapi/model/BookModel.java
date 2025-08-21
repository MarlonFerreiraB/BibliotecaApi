package io.github.marlon.bibliotecaapi.bibliotecaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EntityListeners(AuditingEntityListener.class)
public class BookModel {
    private Integer actualyYear = LocalDate.now().getYear();
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank(message = "Nao pode estar em branco")
    @Size(max = 100, message = "Limite de caractere atingido.")
    private String title;
    @NotNull(message = "Nao pode estar em branco")
    @PastOrPresent
    private LocalDate publicationYear;
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel authorModel;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updateAt;

}
