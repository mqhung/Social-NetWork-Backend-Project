package com.project.socialnetwork.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private MessageType type;

    private String content;

    @ManyToOne
    private Conversation conversation;

    @ManyToOne
    private AppUser sender;

    @ManyToOne
    private AppUser receiver;

    private Timestamp createdAt;


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

}
