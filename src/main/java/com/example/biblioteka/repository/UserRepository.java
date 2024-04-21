package com.example.biblioteka.repository;

import com.example.biblioteka.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByNameIgnoreCase(String name);
}
