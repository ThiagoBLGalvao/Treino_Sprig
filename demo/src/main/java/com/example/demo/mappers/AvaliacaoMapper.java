package com.example.demo.mappers;

import com.example.demo.dto.AvaliacaoDto;
import com.example.demo.model.Avaliacao;
import com.example.demo.services.AlunoService;
import com.example.demo.services.MateriaService;
import com.example.demo.services.MentorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AlunoMapper.class, MateriaMapper.class})
public interface AvaliacaoMapper {
    @Mappings({
            @Mapping(target = "id",source = "avaliacao.id"),
            @Mapping(target = "nota",source = "avaliacao.nota"),
            @Mapping(target = "mes",source = "avaliacao.mes"),
            @Mapping(target = "mentor_id",source = "avaliacao.mentor.id"),
            @Mapping(target = "aluno_id",source = "avaliacao.aluno.id"),
            @Mapping(target = "materia_id",source = "avaliacao.materia.id"),
            @Mapping(target = "materiaDto", source = "avaliacao.materia"),
            @Mapping(target = "alunoDto", source = "avaliacao.aluno")
    })
    AvaliacaoDto avaliacaoToAvaliacaoDto(Avaliacao avaliacao);

    Avaliacao dtoToAvaliacao(AvaliacaoDto dto, AlunoService alunoService, MentorService mentorService, MateriaService materiaService);
}
