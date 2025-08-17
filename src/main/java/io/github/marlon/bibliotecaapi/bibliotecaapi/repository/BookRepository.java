package io.github.marlon.bibliotecaapi.bibliotecaapi.repository;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, String> {
    BookModel findByTitleContainingIgnoreCase(String title);
    void deleteByTitle(String title);
}
