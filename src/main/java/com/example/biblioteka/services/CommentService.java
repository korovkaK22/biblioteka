package com.example.biblioteka.services;

import com.example.biblioteka.entity.Comment;
import com.example.biblioteka.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findCommentById(Integer id) {
        return commentRepository.findById(id);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    public Comment updateComment(Integer id, Comment comment) {
        return commentRepository.findById(id)
                .map(existingComment -> {
                    existingComment.setRate(comment.getRate());
                    existingComment.setComment(comment.getComment());
                    return commentRepository.save(existingComment);
                })
                .orElseThrow(() -> new RuntimeException("Comment not found with id " + id));
    }
}
