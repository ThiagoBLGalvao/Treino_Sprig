package com.example.demo.mappers;

import com.example.demo.dto.MentorDto;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class})
public interface MentorMentorDtoMapper {
//    @Mappings({
//            @Mapping(target = "id", source = "entity.id"),
//            @Mapping(target = "name", source = "entity.name")
//    })
    MentorDto entityToDto(Mentor entity);

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "name", source = "entity.name"),
            @Mapping(target = "alunos", source = "setAlunos")
    })
    MentorDto entityAndSetToDto(Mentor entity, Set<Aluno> setAlunos);


    Mentor dtoToEntity(MentorDto dto);
}
