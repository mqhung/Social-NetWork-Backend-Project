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
    private enum status{
        PUBLIC,
        FRIENDONLY,
        PRIVATE
    }
    private String content;
    private Timestamp createdTime;
    private String image;

}
