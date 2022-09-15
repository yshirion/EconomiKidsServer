package com.example.server2.familyEntity;

import com.example.server2.investEntity.Invest;
import com.example.server2.userEntity.User;
import com.example.server2.userEntity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Family> findUserById(@PathVariable(value = "id") int id) {
        Optional<Family> family = familyRepository.findById(id);
        if(family.isPresent()) {
            return ResponseEntity.ok().body(family.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/save")
    public void saveFamily(Family family) {
        familyRepository.save(family);
    }

}