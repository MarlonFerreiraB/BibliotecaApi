package io.github.marlon.bibliotecaapi.bibliotecaapi.repository;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel,String> {

    List<AuthorModel> findByNameContainingIgnoreCase(String name);
}
