package com.project.socialnetwork.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private AppUser firstUser;

    @ManyToOne
    private AppUser secondUser;

    private Timestamp createdAt;

    private Timestamp updateAt;



}
