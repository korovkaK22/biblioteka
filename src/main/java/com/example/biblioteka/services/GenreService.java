package com.example.biblioteka.services;

import com.example.biblioteka.entity.Genre;
import com.example.biblioteka.entity.Writing;
import com.example.biblioteka.repository.GenreRepository;
import com.example.biblioteka.repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAllWritings() {
        return genreRepository.findAll();
    }
}
