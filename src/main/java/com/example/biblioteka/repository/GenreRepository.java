package com.example.biblioteka.repository;

import com.example.biblioteka.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    // CRUD operations are already provided by JpaRepository.
}
