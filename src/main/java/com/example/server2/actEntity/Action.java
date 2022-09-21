package com.example.server2.actEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Aid;
    private long user;
    private boolean positive;
    private String type;
    private double amount;
    private LocalDateTime start;

    public Action() {
    }

    public Action(long user, boolean positive, String type, double amount, LocalDateTime start) {
        this.user = user;
        this.positive = positive;
        this.type = type;
        this.amount = amount;
        this.start = start;
    }

    public long getAid() {
        return Aid;
    }

    public void setAid(long aid) {
        Aid = aid;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
}
