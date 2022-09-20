package com.example.server2.loanEntity;

import com.example.server2.actEntity.Action;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lid;
    private double currentAmount, interest;
    private long user;
    private double amount;
    private LocalDate start;

    public Loan() {
    }

    public Loan(long lid, double currentAmount, double interest, long user, double amount, LocalDate start) {
        this.lid = lid;
        this.currentAmount = currentAmount;
        this.interest = interest;
        this.user = user;
        this.amount = amount;
        this.start = start;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }
}
