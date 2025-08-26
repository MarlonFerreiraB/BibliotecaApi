package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.BookService;
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
    public ResponseEntity<BookModel> createBook(@RequestBody BookCreationDTO bookCreationDTO){

        BookModel bookModel = bookService.saveBook(bookCreationDTO);

       URI location = ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("{id}")
               .buildAndExpand(bookModel.getId())
               .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<BookModel> findAuthorByTitle(@RequestParam  String name){
        BookModel bookModel = bookService.findByBook(name);
        if(bookModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable String id, @RequestBody BookCreationDTO bookCreationDTO){
        BookModel bookUpdate = bookService.updateBook(id, bookCreationDTO);
        if(bookUpdate == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bookUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id){
        Optional<BookModel> bookModel = bookRepository.findById(id);
        if(!bookModel.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
