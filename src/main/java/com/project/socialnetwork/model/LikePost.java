package com.project.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private Long likerId;

    public LikePost() {
    }

    public LikePost(Long id, Long postId, Long likerId) {
        this.id = id;
        this.postId = postId;
        this.likerId = likerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getLikerId() {
        return likerId;
    }

    public void setLikerId(Long likerId) {
        this.likerId = likerId;
    }
}
