package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("author")
public class AuhorController {

    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;

    @PostMapping()
    public ResponseEntity<AuthorModel> createAuthor(@RequestBody AuthorModel authorModel){
        AuthorModel nvAuthor = authorService.saveAuthor(authorModel);
        return new ResponseEntity<>(nvAuthor, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorModel>> findbyAuthor(@PathVariable String name){
        List<AuthorModel> authorModelList = authorService.findByNameContainingIgnoreCase(name);
        if(authorModelList .isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorModelList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthorById(String id){
        Optional<AuthorModel> optionalAuthorModel = authorRepository.findById(id);

        if(!optionalAuthorModel.isPresent()){
            return ResponseEntity.notFound().build();
        }
        authorService.deleteAuthor(optionalAuthorModel.get().getName());
        return ResponseEntity.noContent().build();
    }
}
