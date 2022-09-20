package com.example.server2.loanEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
        @Query(value = "SELECT * FROM loans WHERE loans.user = ?",nativeQuery = true)
        List<Loan> findByUid(long user);

}
