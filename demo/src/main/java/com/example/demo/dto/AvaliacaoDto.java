package com.example.demo.dto;

import com.example.demo.model.Avaliacao;

import java.io.Serializable;
import java.time.Month;
import java.util.Objects;

public class AvaliacaoDto implements Serializable {
    private static final long  serialVersionUID=1L;

    private Long id;
    private Double nota;
    private Month mes;
    private Long mentor_id;
    private Long aluno_id;
    private Long materia_id;

    public AvaliacaoDto() {
    }

    public AvaliacaoDto(Avaliacao entity){
        this.id = entity.getId();
        this.nota = entity.getNota();
        this.mes = entity.getMes();
        this.aluno_id = entity.getAluno().getId();
        this.mentor_id = entity.getMentor().getId();
        this.materia_id = entity.getMateria().getId();
    }

    public AvaliacaoDto(Double nota, Month mes, Long mentor_id, Long aluno_id, Long materia_id) {
        this.nota = nota;
        this.mes = mes;
        this.mentor_id = mentor_id;
        this.aluno_id = aluno_id;
        this.materia_id = materia_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Month getMes() {
        return mes;
    }

    public void setMes(Month mes) {
        this.mes = mes;
    }

    public Long getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(Long mentor_id) {
        this.mentor_id = mentor_id;
    }

    public Long getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Long aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Long getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(Long materia_id) {
        this.materia_id = materia_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoDto that = (AvaliacaoDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
