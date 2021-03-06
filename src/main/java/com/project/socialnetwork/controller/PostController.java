package com.project.socialnetwork.controller;


import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Post;
import com.project.socialnetwork.model.PostStatus;
import com.project.socialnetwork.service.postService.IPostService;
import com.project.socialnetwork.service.postStatus.IPostStatusService;
import com.project.socialnetwork.service.user.IUserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {

    @Autowired
    RelationshipController relationshipController;

    @Autowired
    IPostService postService;

    @Autowired
    IUserService userService;
    @Autowired
    IPostStatusService postStatusService;

    @GetMapping("/get-post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {

        Post post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/get-all-post-by-user-id/{id}")
    public ResponseEntity<List<Post>> getAllPostByUserId(@PathVariable Long id) {
        AppUser guestUser = userService.findById(id);

        return new ResponseEntity<>(postService.findAllByAppUser(guestUser), HttpStatus.OK);
    }

    @GetMapping("/get-all-post")
    public ResponseEntity<List<Post>> getAllMyPost() {

        AppUser currentUser = userService.getCurrentUser();

        List<Post> postList = postService.findAllByAppUser(currentUser);
        Collections.sort(postList, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getCreatedTime().getTime() > o2.getCreatedTime().getTime()) {
                    return 1;
                } else if (o1.getCreatedTime().getTime() < o2.getCreatedTime().getTime()) {
                    return -1;
                }
                return 0;
            }
        });


        return new ResponseEntity<>(postList, HttpStatus.OK);
    }


    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {

//        AppUser currentUser = new AppUser();
//        currentUser.setId(1L);
        AppUser currentUser = userService.getCurrentUser();

        //to show the deleted post
        Post p = postService.findById(id);
        //check
        if (currentUser.equals(p.getAppUser())) {
            postService.deleteById(id);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/create-new-post")
    public ResponseEntity<Post> createNewPost(@RequestBody Post post) {
        AppUser currentUser = userService.getCurrentUser();

        post.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        post.setAppUser(currentUser);


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


    @GetMapping("/get-current-user")
    public ResponseEntity<AppUser> getCurrentUser() {
        AppUser currentUser = userService.getCurrentUser();
        //delete password before send to client
        currentUser.setPassword(null);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = userService.findById(id);
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get-Post-status")
    public ResponseEntity<List<PostStatus>> getAllPostStatus() {
        return new ResponseEntity<>(postStatusService.findALl(), HttpStatus.OK);
    }

    @GetMapping("/get-all-friend-post")
    public ResponseEntity<List<Post>> getAllFriendPost() {
        List<AppUser> myFriends = relationshipController.findAllFriend(userService.getCurrentUser().getId());

        AppUser currentUser = userService.getCurrentUser();
        List<Post> postList = postService.findAllByAppUser(currentUser);
        for (AppUser appUser : myFriends) {
            postList.addAll(postService.findAllByAppUser(appUser));
        }

        Collections.sort(postList, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getCreatedTime().getTime() > o2.getCreatedTime().getTime()) {
                    return 1;
                } else if (o1.getCreatedTime().getTime() < o2.getCreatedTime().getTime()) {
                    return -1;
                }
                return 0;
            }
        });

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("find-post-by-content/{id}/{keyWord}")
    public ResponseEntity<?> findPostByContent(@PathVariable String keyWord, @PathVariable Long id) {
        String content = "%" + keyWord + "%";

        List<Post> postList = postService.findPostByContent(id, content);

        Collections.sort(postList, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getCreatedTime().getTime() > o2.getCreatedTime().getTime()) {
                    return 1;
                } else if (o1.getCreatedTime().getTime() < o2.getCreatedTime().getTime()) {
                    return -1;
                }
                return 0;
            }
        });

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }


}
