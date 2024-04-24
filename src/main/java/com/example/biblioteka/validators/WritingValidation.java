package com.example.biblioteka.validators;

import com.example.biblioteka.entity.Writing;
import com.example.biblioteka.web.restcontrollers.WritingController;

import java.util.Optional;

public class WritingValidation {

    public void validateWriting(Optional<Writing> writing, int page){
        if (writing.isEmpty()){
            throw new IllegalArgumentException("Illegal writing");
        }
        if (page <=0){
            throw new IllegalArgumentException("Illegal page");
        }
    }


}
