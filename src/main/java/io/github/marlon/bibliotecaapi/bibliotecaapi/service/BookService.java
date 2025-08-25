package io.github.marlon.bibliotecaapi.bibliotecaapi.service;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public BookModel saveBook(BookDTO bookDTO){
        BookModel bookModel = new BookModel();
        bookModel.setTitle(bookDTO.getTitle());
        bookModel.setAvailable(bookDTO.isAvailable());
        bookModel.setGeneroEnum(bookDTO.getGeneroEnum());
        bookModel.setPublicationYear(bookDTO.getPublicationYear());

        AuthorModel authorModel = authorRepository.findById(bookDTO.getAuthor_id()).orElseThrow(() -> new RuntimeException("n foi possivel achar o autor"));
        bookModel.setAuthorModel(authorModel);

        return bookRepository.save(bookModel);
    }
    @Transactional(readOnly = true)
    public BookModel findByBook(String name){
        return bookRepository.findByTitleIgnoreCase(name);
    }
    @Transactional
    public BookModel updateBook(String id, BookDTO bookDTO){
        if(!bookRepository.existsById(id)) return null;

        BookModel bookModel = new BookModel();
        bookModel.setId(id);
        bookModel.setTitle(bookDTO.getTitle());
        bookModel.setAvailable(bookDTO.isAvailable());
        bookModel.setGeneroEnum(bookDTO.getGeneroEnum());
        bookModel.setPublicationYear(bookDTO.getPublicationYear());

        return bookRepository.save(bookModel);
    }
    @Transactional
    public void deleteBook(String name){
        bookRepository.deleteByTitle(name);
    }
}
