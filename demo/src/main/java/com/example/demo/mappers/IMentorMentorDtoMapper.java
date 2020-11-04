package com.example.demo.mappers;

import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.MentorDto;
import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;

import java.util.Set;

public class IMentorMentorDtoMapper implements MentorMentorDtoMapper{
    @Override
    public MentorDto entityToDto(Mentor entity) {
        if(entity == null){
            return null;
        }
        MentorDto dto = new MentorDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());

        return dto;
    }

    @Override
    public MentorDto entityAndSetToDto(Mentor entity, Set<Aluno> setAlunos) {
        if(entity == null){
            return null;
        }
        MentorDto dto = new MentorDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        setAlunos.forEach(x->dto.getAlunos().add(new AlunoDto(x)));

        return dto;
    }

    @Override
    public Mentor dtoToEntity(MentorDto dto) {
        return null;
    }
}
