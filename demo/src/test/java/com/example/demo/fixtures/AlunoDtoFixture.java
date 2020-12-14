package com.example.demo.fixtures;

import com.example.demo.dto.AlunoDto;

public class AlunoDtoFixture {
    public static AlunoDto buildAlunoDtoDefault(){
        return new AlunoDto(AlunoFixture.buildDefaultAluno());
    }
    public static AlunoDto  buildAlunoDtoToupdate(){
        AlunoDto alunoDtoToUpdate = new AlunoDto();
        alunoDtoToUpdate.setName("Jonathan");
        alunoDtoToUpdate.setClassMate("Joseph");
        alunoDtoToUpdate.setMentor_id(1L);
        alunoDtoToUpdate.setPrograma_id(1L);
        return alunoDtoToUpdate;
    }
}
