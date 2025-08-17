package io.github.marlon.bibliotecaapi.bibliotecaapi.repository;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorModel,String> {

    List<AuthorModel> findByNameContainingIgnoreCase(String name);
}
