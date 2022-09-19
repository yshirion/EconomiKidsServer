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
    long destination;
    long sender;
    String subject;
    boolean readed;
    @Column(name = "text", nullable = false, columnDefinition = "BLOB NOT NULL")
    String text;

    public Message() {
    }

    public Message(int id, long sender, long destination, String subject, String text, boolean readed) {
        this.id = id;
        this.sender = sender;
        this.destination = destination;
        this.subject = subject;
        this.text = text;
        this.readed = readed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDestination() {
        return destination;
    }

    public void setDestination(long destination) {
        this.destination = destination;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}
