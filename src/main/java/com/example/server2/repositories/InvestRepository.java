package com.example.server2.repositories;

import com.example.server2.entities.Invest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface InvestRepository extends CrudRepository<Invest, Long>{

    // select all investments of some user, and sort them by date, but reverse, to show the first the new.
    @Query(value = "SELECT * FROM invests WHERE invests.user = ? order by invests.start desc",nativeQuery = true)
    List<Invest> findByUid(long user);

    //Update the date of update and the new amount.
    @Modifying
    @Query("UPDATE Invest i SET i.updateTime =:date, i.currentAmount =:amount WHERE i.Iid =:id")
    void updateInvest(@Param("date") LocalDateTime date, @Param("amount") double amount, @Param("id") long id);

}
