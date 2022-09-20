package com.example.server2.familyEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@
@Transactional
@Repository
public interface FamilyRepository extends CrudRepository<Family, Long> {

    @Modifying
    @Query("UPDATE Family f SET f.loanInterest =:loan, f.investLongInterest =:longI" +
            ", f.investShortInterest=:shortI WHERE f.fId =:ID")
    void updateFamily(@Param("loan") double loan, @Param("longI") double longInvest, @Param("shortI") double shortInvest, @Param("ID") Long id);
}

