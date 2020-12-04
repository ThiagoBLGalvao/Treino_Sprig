package com.example.demo.repository;

import com.example.demo.model.Programa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    @Query(value = "SELECT * FROM tb_programa tbp WHERE tbp.active = true",
            nativeQuery = true)
    List<Programa> findAllActive();

    Page<Programa> findByActive(boolean active, Pageable pageable);
}
