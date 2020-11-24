package com.example.demo.repository;

import com.example.demo.model.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    @Query(value = "SELECT * FROM tb_mentor tbm WHERE tbm.active = true",
            nativeQuery = true)
    List<Mentor> findAllActive();

    Page<Mentor> findByActive(boolean active, Pageable pageRequest);
}
