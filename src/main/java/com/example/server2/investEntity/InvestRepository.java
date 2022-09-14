package com.example.server2.investEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestRepository extends CrudRepository<Invest, Long>{
}
