package com.example.biblioteka.dto;

import com.example.biblioteka.entity.Comment;
import com.example.biblioteka.entity.User;
import com.example.biblioteka.entity.Writing;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    public CommentDto(Comment comment) {
        this.comment = comment.getComment();
        this.id = comment.getId();
        this.user = new UserDto(comment.getUser());
        this.rate = comment.getRate();
        this.comment = comment.getComment();
    }

    private Integer id;


    private UserDto user;

    private Integer rate;


    private String comment;

}
