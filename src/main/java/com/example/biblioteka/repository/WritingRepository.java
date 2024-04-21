package com.example.biblioteka.repository;

import com.example.biblioteka.entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WritingRepository extends JpaRepository<Writing, Integer> {
    // CRUD operations are already provided by JpaRepository.
}
