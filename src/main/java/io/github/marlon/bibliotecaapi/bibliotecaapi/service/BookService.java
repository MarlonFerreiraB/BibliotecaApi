package io.github.marlon.bibliotecaapi.bibliotecaapi.service;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookUpdateDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.BookRepository;
import io.github.marlon.bibliotecaapi.bibliotecaapi.util.BookMapper;
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
    BookMapper bookMapper;

    @Transactional
    public BookResponseDTO saveBook(BookCreationDTO bookCreationDTO) {
        BookModel bookModel = bookMapper.toEntity(bookCreationDTO);
        bookRepository.save(bookModel);
        return bookMapper.toResponse(bookModel);
    }

    @Transactional(readOnly = true)
    public BookResponseDTO findByBook(String name) {
        BookModel bookModel = bookRepository.findByTitleIgnoreCase(name);
        return bookMapper.toResponse(bookModel);
    }

    @Transactional
    public BookResponseDTO updateBook(String id, BookUpdateDTO bookUpdateDTO) {
        BookModel bookModel = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        bookMapper.toEntityUpdate(bookUpdateDTO, bookModel);
        BookModel updatedBook = bookRepository.save(bookModel);
        return bookMapper.toResponse(updatedBook);
    }

    @Transactional
    public void deleteBook(BookModel bookModel) {
        bookRepository.delete(bookModel);
    }
}
