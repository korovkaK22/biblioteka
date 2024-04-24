package com.example.biblioteka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String username;
    private String password;
    private Boolean isDataAnalytics = false;

    // Геттери та сеттери
}
