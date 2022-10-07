package com.example.server2.controllers;

import com.example.server2.repositories.ActRepository;
import com.example.server2.entities.Action;
import com.example.server2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/action")
public class ActController {

    @Autowired
    private ActRepository actRepository;

    @GetMapping("/{uid}")
    public List<Action> findById(@PathVariable(value = "uid") long uid) {
        System.out.println("here");
        List<Action> actions = actRepository.findByUid(uid);
        for (Action action : actions) System.out.println(""+action.getAmount());
        return actions;
    }

    @Autowired
    UserController userController;

    @PostMapping("/save")
    public String saveAction(@RequestBody Action act) {
        //Update the change in user balance.
        User user = userController.findUserById(act.getUser()).getBody();
        if (!act.isPositive() && (user.getBalance() - act.getAmount()) < 0)
            return "Your balance is not enough.";
        double amount;
        //if the action is expense - subtract it from balance, not add.
        if(act.isPositive())
            amount = act.getAmount();
        else {
            amount = act.getAmount() * -1;
        }
        userController.update(act.getUser(), amount);

        //Save the action in database and check it.
        Action action = actRepository.save(act);
        if (action == null) {
            return "not Saved";
        }
        return "saved";
    }
}
