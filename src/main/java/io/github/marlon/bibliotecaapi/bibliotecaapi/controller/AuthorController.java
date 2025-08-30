package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorUpdateDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
        return ResponseEntity.ok().build();
    }
    @GetMapping()
    public ResponseEntity<AuthorResponseDTO> getAuthor(@RequestParam String id){
        AuthorResponseDTO authorResponseDTO = authorService.getAuthor(id);
        return ResponseEntity.ok(authorResponseDTO);
    }
}
