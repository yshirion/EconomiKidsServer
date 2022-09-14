package com.example.server2.messageEntity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    UUID destination;
    UUID sender;
    String subject;
    @Column(name = "text", nullable = false, columnDefinition = "BLOB NOT NULL")
    String text;

    public Message() {
    }

    public Message(int id, UUID sender, UUID destination, String subject, String text) {
        this.id = id;
        this.sender = sender;
        this.destination = destination;
        this.subject = subject;
        this.text = text;
    }

    public long getId() {
        return id;
    }

//    public UUID getSender() {
//        return from;
//    }
//
//    public UUID getDestination() {
//        return destination;
//    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
