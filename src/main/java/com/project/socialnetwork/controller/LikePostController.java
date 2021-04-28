package com.project.socialnetwork.controller;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.LikePost;
import com.project.socialnetwork.model.Post;
import com.project.socialnetwork.service.likePost.ILikePostService;
import com.project.socialnetwork.service.postService.IPostService;
import com.project.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/likepost")
public class LikePostController {
    @Autowired
    ILikePostService likePostService;

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    @GetMapping("")
    public ResponseEntity<Iterable<LikePost>> getAllLikePost() {
        return new ResponseEntity<>(likePostService.findALl(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LikePost> createLikePost(@RequestBody LikePost likePost) {
        Post post = this.postService.findById(likePost.getPostId());
        post.setPostLike(post.getPostLike() + 1);
        this.postService.save(post);
        return new ResponseEntity<>(likePostService.save(likePost), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LikePost> updateLikePost(@PathVariable Long id, @RequestBody LikePost likePost) {
        likePost.setId(id);
        return new ResponseEntity<>(likePostService.save(likePost), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteLikePost(@PathVariable Long id) {
        Post post = this.postService.findById(this.likePostService.findById(id).getPostId());
        post.setPostLike(post.getPostLike() - 1);
        this.postService.save(post);
        likePostService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findLikerByPostId/{postId}")
    public ResponseEntity<List<AppUser>> findLikePostByPostId(@PathVariable Long postId) {
        List<LikePost> likePosts = likePostService.findLikePostByPostId(postId);
        List<AppUser> likerList = new ArrayList<>();
        for (LikePost likePost : likePosts) {
            likerList.add(this.userService.findById(likePost.getLikerId()));
        }
        return new ResponseEntity<>(likerList, HttpStatus.OK);
    }
}
