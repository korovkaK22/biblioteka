package com.example.biblioteka.services;

import com.example.biblioteka.entity.Author;
import com.example.biblioteka.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(Integer id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Integer id, Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    existingAuthor.setSurname(author.getSurname());
                    existingAuthor.setAvatarUrl(author.getAvatarUrl());
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(() -> new RuntimeException("Author not found with id " + id));
    }
}
