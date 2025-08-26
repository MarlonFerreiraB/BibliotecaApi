package io.github.marlon.bibliotecaapi.bibliotecaapi.utils;

import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorCreationDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.dto.AuthorResponseDTO;
import io.github.marlon.bibliotecaapi.bibliotecaapi.model.AuthorModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDTO toDtoResponse(AuthorModel authorModel);

    AuthorModel toEntity(AuthorCreationDTO authorCreationDTO);
}
