package com.example.demo.fixtures;

import com.example.demo.model.Avaliacao;

import java.time.Month;

public class AvaliacaoFixture {
    public static Avaliacao buildAvaliacaoDefault(){
        return new Avaliacao(8d, Month.of(8), MentorFixture.buildMentorDefault(), AlunoFixture.buildDefaultAluno(), MateriaFixture.buildMateriaDefault());
    }
}
