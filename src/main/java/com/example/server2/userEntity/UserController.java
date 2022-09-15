package com.example.server2.userEntity;

import com.example.server2.actEntity.ActController;
import com.example.server2.familyEntity.FamilyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/family/{FId}")
    public List<User> findByFamily(@PathVariable(value = "FId") Long Fid){
        List<User> users = userRepository.findByFamilyId(Fid);
        return users;
    }


    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}