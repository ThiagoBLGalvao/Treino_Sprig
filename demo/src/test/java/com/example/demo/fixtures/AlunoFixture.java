package com.example.demo.fixtures;

import com.example.demo.model.Aluno;

public class AlunoFixture {
    public static Aluno buildDefaultAluno(){
        return new Aluno(1L, "Joseph", "Dio",MentorFixture.buildMentorDefault(),ProgramaFixture.buildProgramaDefault(), true);
    }
}
