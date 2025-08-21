package io.github.marlon.bibliotecaapi.bibliotecaapi.service;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookModel saveBook(BookModel bookModel){
        return bookRepository.save(bookModel);
    }
    @Transactional(readOnly = true)
    public BookModel findByBook(String name){
        return bookRepository.findByTitleIgnoreCase(name);
    }
    @Transactional
    public BookModel updateBook(String id, BookModel bookModel){
        if(!bookRepository.existsById(id)) return null;
        bookModel.setId(id);
        return bookRepository.save(bookModel);
    }
    @Transactional
    public void deleteBook(String name){
        bookRepository.deleteByTitle(name);
    }
}
