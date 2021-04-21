package com.project.socialnetwork.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private RelationshipStatus status;
    @ManyToOne
    private AppUser firstUser;
    @ManyToOne
    private AppUser secondUser;


}
