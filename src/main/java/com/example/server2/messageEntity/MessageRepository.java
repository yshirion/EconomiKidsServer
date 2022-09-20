package com.example.server2.messageEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface MessageRepository extends CrudRepository<Message,Long> {
    @Query(value = "SELECT * FROM messages WHERE messages.sender = ?",nativeQuery = true)
    List<Message> findBySender(long sender);
    @Query(value = "SELECT * FROM messages WHERE messages.destination = ?",nativeQuery = true)
    List<Message> findByDestination(long destination);
}
