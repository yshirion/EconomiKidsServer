package com.example.server2.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lid;
    private double currentAmount, interest;
    private long user;
    private double amount;
    private LocalDateTime start;
    private LocalDateTime updateTime;

    public Loan() {
    }

    public Loan(long lid, double currentAmount, double interest, long user, double amount, LocalDateTime start, LocalDateTime updateTime) {
        this.lid = lid;
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.user = user;
        this.amount = amount;
        this.start = start;
        this.updateTime = updateTime;
    }

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
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

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
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


    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
