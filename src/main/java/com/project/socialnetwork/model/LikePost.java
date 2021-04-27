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

    @ManyToOne
    private AppUser appUser;

    public LikePost() {
    }

    public LikePost(Long id, Long postId, AppUser appUser) {
        this.id = id;
        this.postId = postId;
        this.appUser = appUser;
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
