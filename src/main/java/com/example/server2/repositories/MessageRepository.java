package com.example.server2.repositories;

import com.example.server2.entities.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public interface MessageRepository extends CrudRepository<Message,Long> {

    //get all message of specific user id.
    @Query(value = "SELECT * FROM messages WHERE messages.sender = ?",nativeQuery = true)
    List<Message> findBySender(long sender);

    // select all messages of some user, and sort them by date, but reverse, to show the first the new.
    @Query(value = "SELECT * FROM messages m WHERE m.destination = ? order by m.today desc",nativeQuery = true)
    List<Message> findByDestination(long destination);

    //set the message as 'read'.
    @Modifying
    @Query("UPDATE Message m SET m.readed = true WHERE m.id =:id")
    void  updateIsRead( @Param("id") long id);

}
