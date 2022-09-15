package com.example.server2.actEntity;

import com.example.server2.investEntity.Invest;
import com.example.server2.investEntity.InvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/action")
public class ActController {

    @Autowired
    private ActRepository actRepository;

    @GetMapping("/{uid}")
    public List<Action> findByUUID(@PathVariable(value = "uid") UUID uid) {
        List<Action> actions = actRepository.findByUid(uid);
        return actions;
    }


    @PostMapping("/save")
    public void saveAction(@RequestBody Action act) {
        actRepository.save(act);
    }
}