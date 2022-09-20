package com.example.server2.loanEntity;

import com.example.server2.actEntity.ActController;
import com.example.server2.actEntity.Action;
import com.example.server2.familyEntity.Family;
import com.example.server2.familyEntity.FamilyController;
import com.example.server2.userEntity.User;
import com.example.server2.userEntity.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/{uid}")
    public List<Loan> findByUUID(@PathVariable(value = "uid") long uid) {
        List<Loan> loans = loanRepository.findByUid(uid);
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
        Action action = new Action(loan.getUser(), true,
                "loan", loan.getAmount(), loan.getStart());
        String saved = actController.saveAction(action);
        if (!saved.equals("saved")) return "";

        User user = userController.findUserById(loan.getUser()).getBody();
        Family family = familyController.findFamilyById(user.getFamily_id());
        loan.setInterest(family.getLoanInterest());
        loanRepository.save(loan);
        return "saved";
    }
}