package com.example.demo.mappers;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.model.Avaliacao;
import com.example.demo.services.AlunoService;
import com.example.demo.services.MateriaService;
import com.example.demo.services.MentorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
    @Mappings({
            @Mapping(target = "id",source = "avaliacao.id"),
            @Mapping(target = "nota",source = "avaliacao.nota"),
            @Mapping(target = "mes",source = "avaliacao.mes"),
            @Mapping(target = "mentor_id",source = "avaliacao.mentor.id"),
            @Mapping(target = "aluno_id",source = "avaliacao.aluno.id"),
            @Mapping(target = "materia_id",source = "avaliacao.materia.id"),
            @Mapping(target = "materia_name", source = "avaliacao.materia.name"),
            @Mapping(target = "aluno_name", source = "avaliacao.aluno.name")
    })
    AvaliacaoDto avaliacaoToAvaliacaoDto(Avaliacao avaliacao);

    Avaliacao dtoToAvaliacao(AvaliacaoDto dto, AlunoService alunoService, MentorService mentorService, MateriaService materiaService);
}
