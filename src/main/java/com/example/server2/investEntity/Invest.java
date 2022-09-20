package com.example.server2.investEntity;

import com.example.server2.actEntity.Action;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDate;

@Entity
@Table(name = "invests")
public class Invest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Iid;
    private double currentAmount, interest;
    private LocalDate end;
    private double amount;
    private LocalDate start;
    private long user;
    private boolean longTerm;

    public Invest() {}

    public Invest(long iid, double currentAmount, double interest, LocalDate end, double amount, LocalDate start, long user, boolean longTerm) {
        Iid = iid;
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.end = end;
        this.amount = amount;
        this.start = start;
        this.user = user;
        this.longTerm = longTerm;
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

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
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
}
