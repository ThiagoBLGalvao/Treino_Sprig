package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_programa")
public class Programa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean active;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant beginningDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant endingDate;

    @OneToMany(mappedBy = "programa")
    private Set<Aluno> alunos = new HashSet<>();

    public Programa() {
    }

    public Programa(String name, Instant beginingDate, Instant endingDate, Set<Aluno> alunos) {
        this.name = name;
        this.beginningDate = beginingDate;
        this.endingDate = endingDate;
        this.alunos = alunos;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programa programa = (Programa) o;
        return Objects.equals(id, programa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
