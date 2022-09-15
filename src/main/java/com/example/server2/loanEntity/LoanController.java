package com.example.server2.loanEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;
    @GetMapping
    public String ss(){
        return "ddd";
    }

    @GetMapping("/{uid}")
    public List<Loan> findByUUID(@PathVariable(value = "uid") UUID uid) {
        List<Loan> loans = loanRepository.findByUid(uid);
        return loans;
    }
    @PostMapping("/save")
    public void saveLoan(@RequestBody Loan loan) {
        loanRepository.save(loan);
    }
}