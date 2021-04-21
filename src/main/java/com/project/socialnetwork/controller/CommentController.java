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

//    @GetMapping("/show")
//    public ResponseEntity<Iterable<Comment>> showAll() {
//        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
//    }

    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        AppUser currentUser = new AppUser();
        currentUser.setId(1L);
        comment.setAppUser(currentUser);
        comment.setPostId(postId);
        comment.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        Comment newComment = commentService.save(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
}
