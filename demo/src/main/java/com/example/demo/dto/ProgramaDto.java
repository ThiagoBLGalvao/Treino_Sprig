package com.example.demo.dto;

import com.example.demo.model.Aluno;
import com.example.demo.model.Programa;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProgramaDto implements Serializable {
    private static final long  serialVersionUID=1L;

    private Long id;
    private String name;
    private Instant beginningDate;
    private Instant endingDate;
    private List<AlunoDto> alunos = new ArrayList<>();

    public ProgramaDto() {
    }

    public ProgramaDto(String name, Instant begingDate, Instant endingDate, List<AlunoDto> alunos) {
        this.name = name;
        this.beginningDate = begingDate;
        this.endingDate = endingDate;
        this.alunos = alunos;
    }

    public ProgramaDto(Programa entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.beginningDate = entity.getBeginningDate();
        this.endingDate = entity.getEndingDate();
    }

    public ProgramaDto(Programa entity, Set<Aluno> list) {
        this(entity);
        list.forEach(x -> this.alunos.add(new AlunoDto(x)));
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

    public Instant getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Instant beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Instant getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Instant endingDate) {
        this.endingDate = endingDate;
    }

    public List<AlunoDto> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoDto> alunos) {
        this.alunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramaDto that = (ProgramaDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
