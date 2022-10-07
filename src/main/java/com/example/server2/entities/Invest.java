package com.example.server2.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invests")
public class Invest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Iid;
    private double currentAmount, interest;
    private LocalDateTime end;
    private double amount;
    private LocalDateTime start, updateTime;
    private long user;
    private boolean longTerm;

    public Invest() {}

    public Invest(long iid, double currentAmount, double interest,
                  LocalDateTime end, double amount, LocalDateTime start, long user, boolean longTerm, LocalDateTime updateTime) {
        Iid = iid;
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.end = end;
        this.amount = amount;
        this.start = start;
        this.user = user;
        this.longTerm = longTerm;
        this.updateTime = updateTime;
    }

    public long getIid() {
        return Iid;
    }

    public void setIid(long iid) {
        Iid = iid;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
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

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
