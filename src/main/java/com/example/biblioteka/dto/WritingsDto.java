package com.example.biblioteka.dto;

import com.example.biblioteka.entity.Author;
import com.example.biblioteka.entity.Writing;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WritingsDto {

    public WritingsDto(Writing writings) {
        this.id = writings.getId();
        this.author = writings.getAuthor();
        this.description = writings.getDescription();
        this.dateOfPublication = writings.getDateOfPublication();
    }

    private Integer id;

    private Author author;

    private String description;

    private Date dateOfPublication;


}