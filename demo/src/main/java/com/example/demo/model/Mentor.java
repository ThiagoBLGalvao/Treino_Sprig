package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_mentor")
public class Mentor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean active;

    @OneToMany(mappedBy = "mentor")
    private Set<Aluno> alunos = new HashSet<>();

    @OneToMany(mappedBy = "mentor")
    private Set<Avaliacao> avaliacoes;

    public Mentor() {
    }

    public Mentor(String name){
      this.name = name;
    }

    public Mentor(String name, Boolean active){
        this.name = name;
        this.active = active;
    }

    public Mentor(Long id,String name){
        this.id = id;
        this.name = name;
    }

    public Mentor(String name, Set<Aluno> alunos) {
        this.name = name;
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
        Mentor mentor = (Mentor) o;
        return Objects.equals(id, mentor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
