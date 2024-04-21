package com.example.biblioteka.repository;

import com.example.biblioteka.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // CRUD operations are already provided by JpaRepository.
}
