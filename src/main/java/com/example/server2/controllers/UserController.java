package com.example.server2.controllers;

import com.example.server2.entities.Family;
import com.example.server2.entities.User;
import com.example.server2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //Check if exist user with specific username and password.
    @GetMapping("/check/{name}/{password}")
    public ResponseEntity<User> checkUser(@PathVariable(value = "name") String name, @PathVariable(value = "password") String password){//{//, @RequestParam String password){//
        //System.out.println("yes");
        Optional<User> user = userRepository.checkUser(name,password);
        if(user.isPresent()) {
            //System.out.println("exist");
            return ResponseEntity.ok().body(user.get());
        } else {
            //System.out.println("NOTexist");
            return ResponseEntity.notFound().build();
        }
    }

    //Check if username exist.
    private User findByUSerName(String username){
        Optional<User> user = userRepository.checkUserByName(username);
        return user.orElse(null);
    }

    // Find all users of specific family.
    @GetMapping("/family/{id}")
    public List<User> findByFamily(@PathVariable(value = "id") Long id){
        List<User> users = userRepository.findByFamilyId(id, false);
        for (User user: users) System.out.println(user.getUser_name());
        return users;
    }

    // Find the parent of specific family.
    @GetMapping("/parent/{fid}")
    public ArrayList<User> getParent(@PathVariable(value = "fid") long fid){
        ArrayList<User> parent = userRepository.findParentByFamily(fid);
        return parent;
    }

    @GetMapping("changeParent/{id}")
    public String changeParent(@PathVariable(value = "id") long id){
        userRepository.updateToParent(id);
        return "change";
    }

    @Autowired
    private FamilyController familyController;

    //for register user child.
    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {
        Family family = familyController.findFamilyById(user.getFamily_id());
        if (family == null)
            return "Family not found!";

        if (!family.getName().equals(user.getLast_name()))
            return "Id and name of family not matching.";

        User tempUser = findByUSerName(user.getUser_name());

        if (tempUser != null)
            return "This Username already exist.";

        userRepository.save(user);
        return "saved";
    }

    //for register parent in new family.
    @PostMapping("/saveParent")
    public String saveParent(@RequestBody User user){
        try {
            User tempUser = findByUSerName(user.getUser_name());
            if (tempUser != null)
                return "This Username already exist.";

            Family family = new Family(user.getLast_name());
            family = familyController.saveFamily(family);

            user.setFamily_id(family.getfId());
            userRepository.save(user);
            return "saved";
        }
        //For case of parallel access (same username in one moment)
        catch (Exception e){
            return "This Username already exist.";
        }
    }

    @PostMapping("update")
    public void update(@RequestBody long id, @RequestBody double amount){
        //Update the balance of user by receive new change.
        //first select the old amount, calculate and push the fix value.
        double old_balance = userRepository.getBalance(id);
        double new_balance = old_balance + amount;
        userRepository.updateUser(new_balance, id);
    }

    @DeleteMapping("deleteUser")
    public String deleteUser(@RequestBody User user){
        userRepository.delete(user);
        return "delete";
    }
}