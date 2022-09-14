package com.example.server2.actEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActRepository extends CrudRepository<Action, Long> {}