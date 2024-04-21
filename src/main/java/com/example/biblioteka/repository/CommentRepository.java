package com.example.biblioteka.repository;

import com.example.biblioteka.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // CRUD operations are already provided by JpaRepository.
}
