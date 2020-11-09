package com.example.demo.mappers;

import com.example.demo.dto.MateriaDto;
import com.example.demo.model.Materia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MateriaMapper {
    MateriaDto materiaToMateriaDto(Materia materia);

    Materia dtoToMateria(MateriaDto dto);
}
