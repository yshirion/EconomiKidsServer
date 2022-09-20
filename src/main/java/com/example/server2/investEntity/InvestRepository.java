package com.example.server2.investEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestRepository extends CrudRepository<Invest, Long>{
    @Query(value = "SELECT * FROM invests WHERE invests.user = ?",nativeQuery = true)
    List<Invest> findByUid(long user);
}
