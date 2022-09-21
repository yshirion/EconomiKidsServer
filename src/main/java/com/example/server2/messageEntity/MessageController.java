package com.example.server2.messageEntity;

import com.example.server2.userEntity.User;
import com.example.server2.userEntity.UserController;
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
        for (Message message : list){
            if (message.isReaded())
                messageRepository.updateIsRead(message.getId());
        }
        return "update";
    }

    @Autowired
    UserController userController;

    @PostMapping("/save")
    public String saveMessage(@RequestBody Message message) {
        User sender = userController.findUserById(message.getSender()).getBody();
        message.setSender_name(sender.getFirst_name());
        Message message1 = messageRepository.save(message);
        if (message1 == null)
            return "The message not save.";
        return "saved";
    }
}