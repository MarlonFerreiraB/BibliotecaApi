package io.github.marlon.bibliotecaapi.bibliotecaapi.service;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.utils.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    @Transactional
    public BookModel saveBook(BookCreationDTO bookCreationDTO){
        BookModel bookModel = bookMapper.toEntity(bookCreationDTO);
        AuthorModel authorModel = authorRepository.findById(bookCreationDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("n foi possivel achar o autor"));
        bookModel.setAuthorModel(authorModel);

        return bookRepository.save(bookModel);
    }
    @Transactional(readOnly = true)
    public BookModel findByBook(String name){
        return bookRepository.findByTitleIgnoreCase(name);
    }
    @Transactional
    public BookModel updateBook(String id, BookCreationDTO bookCreationDTO){
        if(!bookRepository.existsById(id)) return null;
        BookModel bookModel = bookMapper.toEntity(bookCreationDTO);

        return bookRepository.save(bookModel);
    }
    @Transactional
    public void deleteBook(String name){
        bookRepository.deleteByTitle(name);
    }
}
