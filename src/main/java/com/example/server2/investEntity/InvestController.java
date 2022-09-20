package com.example.server2.investEntity;

import com.example.server2.actEntity.ActController;
import com.example.server2.actEntity.Action;
import com.example.server2.familyEntity.Family;
import com.example.server2.familyEntity.FamilyController;
import com.example.server2.loanEntity.Loan;
import com.example.server2.loanEntity.LoanRepository;
import com.example.server2.userEntity.User;
import com.example.server2.userEntity.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invest")
public class InvestController {

    @Autowired
    private InvestRepository investRepository;

    @GetMapping("/{uid}")
    public List<Invest> findByUUID(@PathVariable(value = "uid") long uid) {
        List<Invest> invest = investRepository.findByUid(uid);
        return invest;
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
}