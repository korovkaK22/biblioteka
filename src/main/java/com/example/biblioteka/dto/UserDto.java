package com.example.biblioteka.dto;

import com.example.biblioteka.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.isDataAnalytics = user.getIsDataAnalytics();
    }

    private Integer id;


    private String name;


    private Boolean isDataAnalytics =false;

}

