package com.example.server2.controllers;

import com.example.server2.entities.Message;
import com.example.server2.repositories.MessageRepository;
import com.example.server2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/from/{uid}")
    public List<Message> findBySend(@PathVariable(value = "uid") long uid){
        List<Message> messages = messageRepository.findBySender(uid);
        return messages;
    }
    @GetMapping("/to/{uid}")
    public List<Message> findByDest(@PathVariable(value = "uid") long uid){
        List<Message> messages = messageRepository.findByDestination(uid);
        return messages;
    }

    @PostMapping("/update")
    public String updateMessages(@RequestBody List<Message> list){
        // Update all  the no read message to be 'read'.
        for (Message message : list){
            if (message.isReaded())
                messageRepository.updateIsRead(message.getId());
        }
        return "update";
    }

    @Autowired
    UserController userController;

    //
    @PostMapping("/save")
    public String saveMessage(@RequestBody Message message) {
        // Add the first name in all message by his id (this only what the sender send, not the name).
        // ***It seems unnecessary - needs to be updated(can do by the client).***
        User sender = userController.findUserById(message.getSender()).getBody();
        message.setSender_name(sender.getFirst_name());
        Message message1 = messageRepository.save(message);
        if (message1 == null)
            return "The message not save.";
        return "saved";
    }
}