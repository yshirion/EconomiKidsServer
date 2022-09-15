package com.example.server2.investEntity;

import com.example.server2.loanEntity.Loan;
import com.example.server2.loanEntity.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invest")
public class InvestController {

    @Autowired
    private InvestRepository investRepository;
    @GetMapping
    public String ss(){
        return "ddd";
    }

    @GetMapping("/{uid}")
    public List<Invest> findByUUID(@PathVariable(value = "uid") UUID uid) {
        List<Invest> invest = investRepository.findByUid(uid);
        return invest;
    }
    @PostMapping("/save")
    public void saveInvest(@RequestBody Invest invest) {
        investRepository.save(invest);
    }
}