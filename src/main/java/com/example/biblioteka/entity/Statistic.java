package com.example.biblioteka.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_writings", referencedColumnName = "id")
    private Writing writing;

    @Column(nullable = false)
    private Integer value;
}