package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorUpdateDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.exceptions.AuthorNotFoundException;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;


    @PostMapping()
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody @Valid AuthorCreationDTO authorCreationDTO){
        AuthorResponseDTO nvAuthor = authorService.saveAuthor(authorCreationDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(nvAuthor.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /*@GetMapping()
    public ResponseEntity<List<AuthorModel>> findbyAuthor(@PathVariable String name){
        List<AuthorModel> authorModelList = authorService.findByNameContainingIgnoreCase(name);
        if(authorModelList .isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorModelList);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable String id, @RequestBody @Valid AuthorUpdateDTO authorUpdateDTO){
        AuthorResponseDTO updateAuthor = authorService.updateAuthor(id, authorUpdateDTO);
        if(updateAuthor == null) throw new AuthorNotFoundException("Author não encontrado");
        return ResponseEntity.ok(updateAuthor);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAuthorById(@PathVariable String id){
        return authorRepository.findById(id).map(a -> {
            authorService.deleteAuthor(a);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping()
    public ResponseEntity<AuthorResponseDTO> getAuthor(@RequestParam String id){
        AuthorResponseDTO authorResponseDTO = authorService.getAuthor(id);
        if(authorResponseDTO == null) throw new AuthorNotFoundException("Author não encontrado");
        return ResponseEntity.ok(authorResponseDTO);
    }
}
