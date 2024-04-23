package com.example.biblioteka.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "writings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Writing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_of_publication")
    private Date dateOfPublication;

    @Column(name = "local_address_storing", length = 255)
    private String localAddressStoring;

    @ManyToMany
    @JoinTable(
            name = "writings_genres",
            joinColumns = @JoinColumn(name = "writing_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
}