package com.example.biblioteka.dto;

import com.example.biblioteka.entity.Author;
import com.example.biblioteka.entity.Genre;
import com.example.biblioteka.entity.Writing;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WritingsDto {

    public WritingsDto(Writing writings) {
        this.id = writings.getId();
        this.name = writings.getName();
        this.author = writings.getAuthor();
        this.description = writings.getDescription();
        this.dateOfPublication = writings.getDateOfPublication();
        this.genres = writings.getGenres().stream().map(Genre::getName).toList();
    }

    private Integer id;

    private String name;

    private Author author;

    private String description;

    private Date dateOfPublication;

    private List<String> genres;

}