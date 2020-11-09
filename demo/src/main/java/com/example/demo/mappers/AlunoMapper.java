package com.example.demo.mappers;

import com.example.demo.dto.AlunoDto;
import com.example.demo.model.Aluno;
import com.example.demo.model.Avaliacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {AvaliacaoMapper.class})
public interface AlunoMapper {
    @Mappings({
            @Mapping(target = "id", source = "aluno.id"),
            @Mapping(target = "name", source = "aluno.name"),
            @Mapping(target = "classMate", source = "aluno.classMate"),
            @Mapping(target = "mentor_id", source = "aluno.mentor.id"),
            @Mapping(target = "programa_id", source = "aluno.programa.id")
    })
    AlunoDto alunoToAlunoDto(Aluno aluno);

    @Mappings({
            @Mapping(target = "id", source = "aluno.id"),
            @Mapping(target = "name", source = "aluno.name"),
            @Mapping(target = "classMate", source = "aluno.classMate"),
            @Mapping(target = "mentor_id", source = "aluno.mentor.id"),
            @Mapping(target = "programa_id", source = "aluno.programa.id"),
            @Mapping(target = "avaliacaoDtoList", source = "avaliacoes")
    })
    AlunoDto alunoAndSetAvaliacaoToAlunoDto(Aluno aluno, Set<Avaliacao> avaliacoes);

    Aluno dtoToAluno(AlunoDto dto);
}
