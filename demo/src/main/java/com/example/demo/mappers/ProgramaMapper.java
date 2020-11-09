package com.example.demo.mappers;

import com.example.demo.dto.ProgramaDto;
import com.example.demo.model.Programa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramaMapper {
    ProgramaDto programaToDto(Programa programa);

    Programa dtoToPrograma(ProgramaDto dto);
}
