package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<AuthorModel> createAuthor(@RequestBody @Valid AuthorCreationDTO authorCreationDTO){
        AuthorModel nvAuthor = authorService.saveAuthor(authorCreationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(nvAuthor.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping()
    public ResponseEntity<List<AuthorModel>> findbyAuthor(@PathVariable String name){
        List<AuthorModel> authorModelList = authorService.findByNameContainingIgnoreCase(name);
        if(authorModelList .isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorModelList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorModel> updateAuthor(@PathVariable String id, @RequestBody @Valid AuthorCreationDTO authorCreationDTO){
        AuthorModel updateAuthor = authorService.updateAuthor(id, authorCreationDTO);
        if(updateAuthor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updateAuthor);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable String id){
        Optional<AuthorModel> optionalAuthorModel = authorRepository.findById(id);

        if(!optionalAuthorModel.isPresent()){
            return ResponseEntity.notFound().build();
        }
        authorService.deleteAuthor(optionalAuthorModel.get().getName());
        return ResponseEntity.noContent().build();
    }
}
