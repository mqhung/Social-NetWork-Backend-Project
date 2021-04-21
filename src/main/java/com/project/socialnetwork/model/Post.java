package com.project.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser appUser;

    private String content;
    private Timestamp createdTime;
    private String image;
    @ManyToOne
    private PostStatus status;
}
