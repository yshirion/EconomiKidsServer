package com.example.server2.repositories;

import com.example.server2.entities.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActRepository extends CrudRepository<Action, Long> {

    // select all actions of some user, and sort them by date, but reverse, to show the first the new.
    @Query(value = "SELECT * FROM actions WHERE actions.user = ? order by actions.start desc",nativeQuery = true)
    List<Action> findByUid(long user);
}