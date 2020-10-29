package com.example.demo.dto;

import com.example.demo.model.Aluno;
import com.example.demo.model.Avaliacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AlunoDto implements Serializable {
    private static final long  serialVersionUID=1L;

    private Long id;
    private String name;
    private String classMate;
    private Long mentor_id;
    private Long programa_id;
    private List<AvaliacaoDto> avaliacaoDtoList = new ArrayList<>();

    public AlunoDto(){

    }

    public AlunoDto(String name, String classMate){
        this.name = name;
        this.classMate = classMate;
    }

    public AlunoDto(Aluno entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.classMate = entity.getClassMate();
        this.mentor_id = entity.getMentor().getId();
        this.programa_id = entity.getPrograma().getId();
    }

    public AlunoDto(Aluno entity, Set<Avaliacao> avalicoes){
        this(entity);
        avalicoes.forEach(x -> this.avaliacaoDtoList.add(new AvaliacaoDto(x)));
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

    public String getClassMate() {
        return classMate;
    }

    public void setClassMate(String classMate) {
        this.classMate = classMate;
    }

    public Long getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(Long mentor_id) {
        this.mentor_id = mentor_id;
    }

    public List<AvaliacaoDto> getAvaliacaoDtoList() {
        return avaliacaoDtoList;
    }

    public void setAvaliacaoDtoList(List<AvaliacaoDto> avaliacaoDtoList) {
        this.avaliacaoDtoList = avaliacaoDtoList;
    }

    public Long getPrograma_id() {
        return programa_id;
    }

    public void setPrograma_id(Long programa_id) {
        this.programa_id = programa_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoDto alunoDto = (AlunoDto) o;
        return Objects.equals(id, alunoDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
