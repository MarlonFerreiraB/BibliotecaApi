package io.github.marlon.bibliotecaapi.bibliotecaapi.utils;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponseDTO toResponseDto(AuthorModel authorModel);

    BookModel toEntity(BookCreationDTO bookCreationDTO);
}
