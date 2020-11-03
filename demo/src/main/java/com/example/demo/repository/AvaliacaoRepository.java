package com.example.demo.repository;

import com.example.demo.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query(value = "SELECT * FROM tb_avaliacao tbav WHERE tbav.materia_id = ?1",
    nativeQuery = true)
    List<Avaliacao> findAllAvaliacaoByMateriaId(Long id_materia);
}
