package com.example.biblioteka.services;

import com.example.biblioteka.dto.CommentCreateDto;
import com.example.biblioteka.entity.Comment;
import com.example.biblioteka.entity.User;
import com.example.biblioteka.entity.Writing;
import com.example.biblioteka.repository.CommentRepository;
import com.example.biblioteka.validators.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentValidator commentValidator;

    @Autowired
    UserService userService;
    @Autowired
    WritingService writingService;

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


    public Comment saveComment(CommentCreateDto commentDto, int writingId) throws IllegalArgumentException {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setRate(commentDto.getRate());
        Optional<User> userOpt = userService.findUserByName(commentDto.getUsername());
        Optional<Writing> writingOpt = writingService.findWritingById(writingId);

        commentValidator.preValidation(userOpt, writingOpt);

        comment.setUser(userOpt.get());
        comment.setWriting(writingOpt.get());
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
