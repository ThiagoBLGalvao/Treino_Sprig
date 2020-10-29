package com.example.demo.repository;

import com.example.demo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
    @Query(value = "SELECT * FROM tb_aluno tba WHERE tba.active = true",
    nativeQuery = true)
    List<Aluno> findAllActive();

    @Query(value = "SELECT * FROM tb_aluno tba WHERE tba.active = true AND tba.mentor_id = ?1",
    nativeQuery = true)
    List<Aluno> findAllActiveAlunosByMentorId(Long mentor_id);

    @Query(value = "SELECT * FROM tb_aluno tba WHERE tba.active = true AND tba.mentor_id = ?1",
            nativeQuery = true)
    Set<Aluno> findSetAllActiveAlunosByMentorId(Long mentor_id);

    @Query(value = "SELECT * FROM tb_aluno tba WHERE tba.active = true AND tba.programa_id = ?1",
            nativeQuery = true)
    List<Aluno> findAllActiveAlunosByProgramaId(Long programa_id);

    @Query(value = "SELECT * FROM tb_aluno tba WHERE tba.active = true AND tba.id = ?1",
            nativeQuery = true)
    Aluno findOneActiveAlunosById(Long id);

    @Query(value = "SELECT * FROM tb_aluno tba WHERE tba.id = ?1 AND tba.active = true AND tba.mentor_id = ?2 AND",
    nativeQuery = true)
    Aluno verifyRelation(Long aluno_id, Long mentor_id);
}
