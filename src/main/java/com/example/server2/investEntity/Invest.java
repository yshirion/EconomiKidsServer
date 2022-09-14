package com.example.server2.investEntity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "invests")
public class Invest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double investAmount, currentAmount, interest;
    UUID user;
    Date start, end;
    boolean longTerm;

    protected Invest() {}

    public Invest(int id, double investAmount,
                  double currentAmount, double interest, UUID user,
                  Date start, Date end, boolean longTerm) {
        this.id = id;
        this.investAmount = investAmount;
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.user = user;
        this.start = start;
        this.end = end;
        this.longTerm = longTerm;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(double investAmount) {
        this.investAmount = investAmount;
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

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }
}
