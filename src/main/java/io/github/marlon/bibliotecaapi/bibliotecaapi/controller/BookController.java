package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookUpdateDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookCreationDTO bookCreationDTO){

        BookResponseDTO bookResponseDTO = bookService.saveBook(bookCreationDTO);

       URI location = ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("{id}")
               .buildAndExpand(bookResponseDTO.getId())
               .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<BookResponseDTO> findAuthorByTitle(@RequestParam  String name){
        BookResponseDTO bookModel = bookService.findByBook(name);
        if(bookModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable String id, @RequestBody BookUpdateDTO bookUpdateDTO){
        BookResponseDTO bookUpdate = bookService.updateBook(id, bookUpdateDTO);
        if(bookUpdate == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bookUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id){
        Optional<BookModel> bookModel = bookRepository.findById(id);
        if(bookModel.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
