package com.example.server2.messageEntity;

import com.example.server2.userEntity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<Message,Long> {
    @Query(value = "SELECT * FROM messages WHERE messages.sender = ?",nativeQuery = true)
    List<Message> findBySender(UUID sender);
    @Query(value = "SELECT * FROM messages WHERE messages.destination = ?",nativeQuery = true)
    List<Message> findByDestination(UUID destination);
}
