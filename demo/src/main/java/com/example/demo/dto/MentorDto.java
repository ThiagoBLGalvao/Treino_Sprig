package com.example.demo.dto;

import com.example.demo.model.Aluno;
import com.example.demo.model.Mentor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MentorDto implements Serializable {
    private static final long  serialVersionUID=1L;

    private Long id;
    private String name;
    private List<AlunoDto> alunos = new ArrayList<>();

    public MentorDto() {
    }

    public MentorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MentorDto(Mentor entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
    
    public MentorDto(Mentor entity, Set<Aluno> alunos){
        this(entity);
        alunos.forEach(x -> this.alunos.add(new AlunoDto(x)));
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlunoDto> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoDto> alunos) {
        this.alunos = alunos;
    }
}