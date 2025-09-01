package io.github.marlon.bibliotecaapi.bibliotecaapi.util;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorUpdateDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorModel toEntity(AuthorCreationDTO authorCreationDTO);

    @Mapping(target = "books", source = "books")
    AuthorResponseDTO toResponse(AuthorModel authorModel);

    BookResponseDTO toResponse(BookModel bookModel);

    AuthorModel toEntityUpdate(AuthorUpdateDTO authorUpdateDTO);
}
