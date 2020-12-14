package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_aluno")
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String classMate;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "programa_id")
    private Programa programa;

    @OneToMany(mappedBy = "aluno")
    private Set<Avaliacao> avaliacoes = new HashSet<>();


    public Aluno() {
    }

    public Aluno(Long id, String name, String classMate, Mentor mentor, Programa programa, Boolean active) {
        this.id = id;
        this.name = name;
        this.classMate = classMate;
        this.mentor = mentor;
        this.programa = programa;
        this.active = active;
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

    public Mentor getMentor() {
        return mentor;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Set<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
