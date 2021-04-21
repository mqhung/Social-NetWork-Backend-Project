package com.project.socialnetwork.controller;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Comment;
import com.project.socialnetwork.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        AppUser currentUser = new AppUser();
        //code tạm để test postman
        currentUser.setId(1L);
        comment.setAppUser(currentUser);
        comment.setPostId(postId);
        comment.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        Comment newComment = commentService.save(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
}
