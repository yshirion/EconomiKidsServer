package com.example.server2.controllers;

import com.example.server2.entities.Action;
import com.example.server2.entities.Family;
import com.example.server2.entities.Invest;
import com.example.server2.repositories.InvestRepository;
import com.example.server2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invest")
public class InvestController {

    @Autowired
    private InvestRepository investRepository;

    //Send all investments by user id, and update the amount by the interest.
    @GetMapping("/{uid}")
    public List<Invest> findById(@PathVariable(value = "uid") long uid) {
        System.out.println();
        List<Invest> invests = investRepository.findByUid(uid);
        LocalDateTime today = LocalDateTime.now();

        // for each invest update the time and the amount in the DB and in itself.
        for (Invest invest : invests){
            System.out.println(invest.getIid());
            int gap = gapMonth(invest.getUpdateTime(),today);
            double newAmount = yieldInterest(invest.getCurrentAmount(), invest.getInterest(), gap);
            invest.setUpdateTime(today);
            invest.setCurrentAmount(newAmount);
            investRepository.updateInvest(today, newAmount, invest.getIid());
        }
        return invests;
    }

    @Autowired
    private ActController actController;
    @Autowired
    private FamilyController familyController;
    @Autowired
    private UserController userController;

    @PostMapping("/save")
    public String saveInvest(@RequestBody Invest invest) {
        User user = userController.findUserById(invest.getUser()).getBody();
        // Cant to be in minus.
        if((user.getBalance() - invest.getAmount()) < 0 )
            return "Your balance is not enough.";

        // First save the investment as 'action'.
        Action action = new Action(invest.getUser(), false,
                "invest", invest.getAmount(), invest.getStart());
        String saved = actController.saveAction(action);
        if (!saved.equals("saved")){
            return "not saved";
        }
        //set interest of this invest by its term and the percent of interest in his family.
        Family family = familyController.findFamilyById(user.getFamily_id());
        if(invest.isLongTerm())
            invest.setInterest(family.getInvestLongInterest());
        else invest.setInterest(family.getInvestShortInterest());
        investRepository.save(invest);

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
    public String delete(@RequestBody List<Invest> invests){
        for (Invest invest : invests)
        {
            investRepository.delete(invest);
            Action action = new Action(invest.getUser(), true, "return invest", invest.getCurrentAmount(), LocalDateTime.now());
            actController.saveAction(action);
        }
        return "delete";
    }
}