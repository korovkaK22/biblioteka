package com.example.biblioteka.web.restcontrollers;

import com.example.biblioteka.dto.WritingsDto;
import com.example.biblioteka.entity.Genre;
import com.example.biblioteka.services.GenreService;
import com.example.biblioteka.services.WritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    private GenreService genreService ;
    @GetMapping
    public ResponseEntity<List<Genre>>  getAllGenres() {
        return ResponseEntity.ok(genreService.findAllWritings());
    }

}






