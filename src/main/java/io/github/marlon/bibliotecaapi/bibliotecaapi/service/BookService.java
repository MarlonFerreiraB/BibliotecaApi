package io.github.marlon.bibliotecaapi.bibliotecaapi.service;

import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public BookModel saveBook(BookModel bookModel){
        return bookRepository.save(bookModel);
    }

    public BookModel findByBook(String name){
        return bookRepository.findByTitleContainingIgnoreCase(name);
    }

    public void deleteBook(String name){
        bookRepository.deleteByTitle(name);
    }
}
