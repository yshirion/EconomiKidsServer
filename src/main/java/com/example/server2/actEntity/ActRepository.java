package com.example.server2.actEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActRepository extends CrudRepository<Action, Long> {
    @Query(value = "SELECT * FROM actions WHERE actions.user = ?",nativeQuery = true)
    List<Action> findByUid(UUID user);
}