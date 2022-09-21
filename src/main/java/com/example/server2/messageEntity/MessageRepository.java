package com.example.server2.messageEntity;

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

    @Query(value = "SELECT * FROM messages WHERE messages.sender = ?",nativeQuery = true)
    List<Message> findBySender(long sender);

    @Query(value = "SELECT * FROM messages m WHERE m.destination = ? order by m.today desc",nativeQuery = true)
    List<Message> findByDestination(long destination);

    @Modifying
    @Query("UPDATE Message m SET m.readed = true WHERE m.id =:id")
    void  updateIsRead( @Param("id") long id);

}
