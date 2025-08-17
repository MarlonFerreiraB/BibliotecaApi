package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<BookModel> createBook(@RequestBody BookModel bookModel){
        BookModel bookModel1 = bookService.saveBook(bookModel);
        return new ResponseEntity<>(bookModel1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BookModel> findAuthorByTitle(@RequestBody  String name){
        BookModel bookModel = bookService.findByBook(name);
        if(bookModel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@RequestBody String id){
        Optional<BookModel> bookModel = bookRepository.findById(id);
        if(!bookModel.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
