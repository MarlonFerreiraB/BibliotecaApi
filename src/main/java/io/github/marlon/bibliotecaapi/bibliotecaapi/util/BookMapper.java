package io.github.marlon.bibliotecaapi.bibliotecaapi.util;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.BookResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.BookModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookModel toEntity(BookCreationDTO bookCreationDTO);

    BookResponseDTO toResponse(BookModel bookModel);

    List<BookResponseDTO> toResponseList(List<BookModel> bookModels);
}
