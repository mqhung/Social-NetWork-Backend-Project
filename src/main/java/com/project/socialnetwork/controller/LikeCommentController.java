package com.project.socialnetwork.controller;

import com.project.socialnetwork.model.AppUser;
import com.project.socialnetwork.model.Comment;
import com.project.socialnetwork.model.LikeComment;
import com.project.socialnetwork.service.comment.ICommentService;
import com.project.socialnetwork.service.likecomment.ILikeCommentService;
import com.project.socialnetwork.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/like/comment")
@CrossOrigin("*")
public class LikeCommentController {
    @Autowired
    private ILikeCommentService likeCommentService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ResponseEntity<Iterable<LikeComment>> showAllLikeComment(){
        return new ResponseEntity<>(likeCommentService.findAllLikeComment(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<LikeComment> createLikeComment(@RequestBody LikeComment likeComment){
        Comment comment = this.commentService.findById(likeComment.getCommentId());
        comment.setCommentLike(comment.getCommentLike() + 1);
        this.commentService.save(comment);
        return new ResponseEntity<>(likeCommentService.saveLikeComment(likeComment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLikeComment(@PathVariable Long id){
        Comment comment = this.commentService.findById(this.likeCommentService.findLikeCommentById(id).getCommentId());
        comment.setCommentLike(comment.getCommentLike() - 1);
        this.commentService.save(comment);
        likeCommentService.deleteLikeComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findByCommentId/{commentId}")
    public  ResponseEntity<Iterable<LikeComment>> getAllLikeCommentById(@PathVariable Long commentId) {
        return new ResponseEntity<>(likeCommentService.findLikeCommentsByCommentId(commentId),HttpStatus.OK);
    }

    @GetMapping("/findLikerByCommentId/{commentId}")
    public ResponseEntity<Iterable<AppUser>> findLikePostByPostId(@PathVariable Long commentId) {
        Iterable<LikeComment> likeComments = likeCommentService.findLikeCommentsByCommentId(commentId);
        List<AppUser> likerList = new ArrayList<>();
        for (LikeComment likeComment: likeComments) {
            likerList.add(this.userService.findById(likeComment.getLikerId()));
        }
        return new ResponseEntity<>( likerList, HttpStatus.OK);
    }

}
