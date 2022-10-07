package com.example.server2.controllers;

import com.example.server2.entities.Family;
import com.example.server2.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping("/{id}")
    public Family findFamilyById(@PathVariable(value = "id") long id) {
        Optional<Family> family = familyRepository.findById(id);
        return family.orElse(null);

    }
    @PostMapping("/save")
    public Family saveFamily(Family family) {
        Family family1;
        family1 = familyRepository.save(family);
        return family1;
    }

    //update all interest.
    @PostMapping("/update")
    public Family update(@RequestBody Family family){
        Family tmp = new Family();
        familyRepository.updateFamily(
                family.getLoanInterest(),
                family.getInvestLongInterest(),
                family.getInvestShortInterest(),
                family.getfId());
        return tmp;
    }

}