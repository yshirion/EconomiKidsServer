package com.example.server2.investEntity;

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
public interface InvestRepository extends CrudRepository<Invest, Long>{

    @Query(value = "SELECT * FROM invests WHERE invests.user = ? order by invests.start desc",nativeQuery = true)
    List<Invest> findByUid(long user);

    @Modifying
    @Query("UPDATE Invest i SET i.updateTime =:date, i.currentAmount =:amount WHERE i.user =:id")
    void updateInvest(@Param("date") LocalDateTime date, @Param("amount") double amount, @Param("id") long id);
}
