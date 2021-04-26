package com.project.socialnetwork.controller;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Comment;
import com.project.socialnetwork.repository.UserRepository;
import com.project.socialnetwork.service.comment.CommentService;
import com.project.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    public IUserService userService;
    @Autowired
    public CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        AppUser currentUser = userService.getCurrentUser();
        comment.setAppUser(currentUser);
//        comment.setPostId(postId);
        comment.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        Comment newComment = commentService.save(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/show/{postId}")
    public ResponseEntity<List<Comment>> showComment(@PathVariable Long postId) {
        List<Comment> commentList = commentService.findCommentByPostId(postId);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> onLoadComment(){
        Comment comment = new Comment();
        comment.setAppUser(userService.getCurrentUser());
        comment.setContent("");
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
