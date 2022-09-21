package com.example.server2.investEntity;

import com.example.server2.actEntity.ActController;
import com.example.server2.actEntity.Action;
import com.example.server2.familyEntity.Family;
import com.example.server2.familyEntity.FamilyController;
import com.example.server2.userEntity.User;
import com.example.server2.userEntity.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/invest")
public class InvestController {

    @Autowired
    private InvestRepository investRepository;

    //Send all investments by user id, and update the amount by the interest.
    @GetMapping("/{uid}")
    public List<Invest> findById(@PathVariable(value = "uid") long uid) {
        List<Invest> invests = investRepository.findByUid(uid);
        LocalDateTime today = LocalDateTime.now();

        for (Invest invest : invests){
            int gap = gapMonth(invest.getUpdateTime(),today);
            double newAmount = yieldInterest(invest.getCurrentAmount(), invest.getInterest(), gap);
            invest.setUpdateTime(today);
            invest.setCurrentAmount(newAmount);
            investRepository.updateInvest(today, newAmount, invest.getUser());
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
        Action action = new Action(invest.getUser(), false,
                "invest", invest.getAmount(), invest.getStart());
        String saved = actController.saveAction(action);
        if (!saved.equals("saved")){
            return "";
        }
        User user = userController.findUserById(invest.getUser()).getBody();
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
}