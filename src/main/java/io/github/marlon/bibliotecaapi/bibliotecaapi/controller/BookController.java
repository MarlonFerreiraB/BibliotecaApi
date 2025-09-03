package io.github.marlon.bibliotecaapi.bibliotecaapi.controller;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookUpdateDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.enums.GeneroEnum;
import io.github.marlon.bibliotecaapi.bibliotecaapi.exceptions.BookNotFoundException;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.service.BookService;
import io.github.marlon.bibliotecaapi.bibliotecaapi.util.BookMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookCreationDTO bookCreationDTO) {

        BookResponseDTO bookResponseDTO = bookService.saveBook(bookCreationDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(bookResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(bookResponseDTO);
    }

   /* @GetMapping
    public ResponseEntity<BookResponseDTO> findBookByTitle(@RequestParam String name) {
        BookResponseDTO bookModel = bookService.findByBook(name);
        if (bookModel == null) throw new BookNotFoundException("Livro não encontrado");
        return ResponseEntity.ok(bookModel);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable String id, @RequestBody BookUpdateDTO bookUpdateDTO) {
        BookResponseDTO bookUpdate = bookService.updateBook(id, bookUpdateDTO);
        if (bookUpdate == null) throw new BookNotFoundException("Livro não encontrado");
        return ResponseEntity.ok(bookUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable String id) {
        return bookRepository.findById(id).map(l -> {
            bookService.deleteBook(l);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> resultSearch
            (@RequestParam(value = "title", required = false) String title,
             @RequestParam(value = "generoEnum", required = false) GeneroEnum generoEnum,
             @RequestParam(value = "anoPublicacao", required = false) Integer publicationYear,
             @RequestParam(value = "author", required = false) String nomeAuthor) {
        var result = bookService.findyFilter(title, generoEnum, publicationYear, nomeAuthor);
        var lista = result.stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

}
