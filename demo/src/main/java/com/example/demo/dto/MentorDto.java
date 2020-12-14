package com.example.demo.dto;

import com.example.demo.model.Mentor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MentorDto implements Serializable {
    private static final long  serialVersionUID=1L;

    private Long id;
    private String name;
    private List<AlunoDto> alunos = new ArrayList<>();

    public MentorDto() {
    }

    public MentorDto(Mentor entity){
        this.id = entity.getId();
        this.name = entity.getName();
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
