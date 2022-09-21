package com.example.server2.loanEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

        @Query(value = "SELECT * FROM loans WHERE loans.user = ? order by loans.start desc",nativeQuery = true)
        List<Loan> findByUid(long user);

        @Modifying
        @Query("UPDATE Loan i SET i.updateTime =:today, i.currentAmount =:newAmount WHERE i.user =:id")
        void updateLoan(@Param("today") LocalDateTime today, @Param("newAmount") double newAmount, @Param("id") long id);
}
