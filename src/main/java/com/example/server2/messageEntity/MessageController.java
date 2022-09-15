package com.example.server2.messageEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    @GetMapping
    public String ss(){
        return "ddd";
    }

    @GetMapping("/from/{uid}")
    public List<Message> findBySend(@PathVariable(value = "uid") UUID uid){
        List<Message> messages = messageRepository.findBySender(uid);
        return messages;
    }
    @GetMapping("/to/{uid}")
    public List<Message> findByDest(@PathVariable(value = "uid") UUID uid){
        List<Message> messages = messageRepository.findByDestination(uid);
        return messages;
    }

    @PostMapping("/save")
    public void saveMessage(@RequestBody Message message) {
        messageRepository.save(message);
    }
}