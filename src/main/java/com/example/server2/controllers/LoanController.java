package com.example.server2.controllers;

import com.example.server2.entities.*;
import com.example.server2.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/{uid}")
    public List<Loan> findById(@PathVariable(value = "uid") long uid) {
        List<Loan> loans = loanRepository.findByUid(uid);
        LocalDateTime today = LocalDateTime.now();

        for (Loan loan : loans){
            int gap = gapMonth(loan.getUpdateTime(),today);
            double newAmount = yieldInterest(loan.getCurrentAmount(), loan.getInterest(), gap);
            loan.setUpdateTime(today);
            loan.setCurrentAmount(newAmount);
            loanRepository.updateLoan(today, newAmount, loan.getLid());
        }
        return loans;
    }

    @Autowired
    private ActController actController;
    @Autowired
    private FamilyController familyController;
    @Autowired
    private UserController userController;

    @PostMapping("/save")
    public String saveLoan(@RequestBody Loan loan) {
        // First save the investment as 'action'.
        Action action = new Action(loan.getUser(), true,
                "loan", loan.getAmount(), loan.getStart());
        String saved = actController.saveAction(action);
        if (!saved.equals("saved")) return "";

        //set interest of this invest by the percent of interest in his family.
        User user = userController.findUserById(loan.getUser()).getBody();
        Family family = familyController.findFamilyById(user.getFamily_id());
        loan.setInterest(family.getLoanInterest());
        loanRepository.save(loan);
        return "saved";
    }

    //Calculate difference between to dates in months.
    private int gapMonth(LocalDateTime start, LocalDateTime end){
        //Set gaps of years and month
        int endMonth = end.getMonth().getValue();
        int startMonth = start.getMonth().getValue();
        int months = endMonth - startMonth;
        int years = end.getYear() - start.getYear();

        //Calculate by: difference between months plus 12 times of difference years
        return months + years * 12;
    }

    //Calculate the yield by function: fv = pv * (1 + r)^n (interest per month).
    private double yieldInterest(double principal, double interest, int gap) {
        double yield = Math.pow((1 + interest / 100),gap);
        return principal * yield;
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody List<Loan> loans){
        for (Loan loan : loans)
        {
            loanRepository.delete(loan);
            Action action = new Action(loan.getUser(), false, "return loan", loan.getCurrentAmount(), LocalDateTime.now());
            actController.saveAction(action);
        }
        return "delete";
    }
}