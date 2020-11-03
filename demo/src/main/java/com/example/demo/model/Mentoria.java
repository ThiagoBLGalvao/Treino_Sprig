package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="tb_mentoria")
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
