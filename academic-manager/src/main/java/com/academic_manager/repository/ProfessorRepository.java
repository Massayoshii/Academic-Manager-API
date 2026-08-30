package com.academic_manager.repository;

import com.academic_manager.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor , Long> {
    boolean existsByEmail(String email);


    boolean existsByEmailAndIdNot(String email, Long id);
}
