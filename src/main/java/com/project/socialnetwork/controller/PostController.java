package com.project.socialnetwork.controller;


import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Post;
import com.project.socialnetwork.service.postService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("")
//@CrossOrigin("*")
public class PostController {

    @Autowired
    IPostService postService;

    @GetMapping("/get-post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {

        Post post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/get-all-post")
    public ResponseEntity<List<Post>> getAllPost() {

        //waiting for method get current user
        AppUser currentUser = new AppUser();
        currentUser.setId(1L);

        List<Post> postList = postService.findAllByAppUser(currentUser);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }


    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {
        //to show the deleted post
        Post p = postService.findById(id);

        postService.deleteById(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/create-new-post")
    public ResponseEntity<Post> createNewPost(@RequestBody Post post) {
        post.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));

        //to show latest created post
        Post p = postService.save(post);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


    @PutMapping("/update-post")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {

        post.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));

        Post p = postService.save(post);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
