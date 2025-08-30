package io.github.marlon.bibliotecaapi.bibliotecaapi.repository;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, String> {
    BookModel findByTitleIgnoreCase(String title);
    void deleteByTitle(String title);
    List<BookModel> findByAuthor_NameContainingIgnoreCase(String name);
}
