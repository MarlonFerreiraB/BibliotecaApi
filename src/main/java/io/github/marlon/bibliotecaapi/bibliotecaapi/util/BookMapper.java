package io.github.marlon.bibliotecaapi.bibliotecaapi.util;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.*;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.repository.AuthorRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java(authorRepository.findById(bookCreationDTO.getAuthorId()).orElse(null))")
    public abstract BookModel toEntity(BookCreationDTO bookCreationDTO);

    @Mapping(target = "authorResponseDTO", source = "author")
    public abstract BookResponseDTO toResponse(BookModel bookModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toEntityUpdate(BookUpdateDTO bookUpdateDTO, @MappingTarget BookModel bookModel);


}
