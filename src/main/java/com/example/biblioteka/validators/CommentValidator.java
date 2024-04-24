package com.example.biblioteka.validators;

import com.example.biblioteka.entity.User;
import com.example.biblioteka.entity.Writing;

import java.util.Optional;
public class CommentValidator {


    public void preValidation(Optional<User> user, Optional<Writing> writing) throws IllegalArgumentException{
        if (writing.isEmpty()){
            throw new IllegalArgumentException("Illegal writing id");
        }
        if (user.isEmpty()){
            throw new IllegalArgumentException("Illegal username");
        }
    }
}
