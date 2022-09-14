package com.example.server2.familyEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "families")
public class Family {
    @Id
    UUID fId;
    String name;
    double loanInterest, investLongInterest, investShortInterest;

    protected Family(){}
    public Family(String name, UUID fId,
                  double loanInterest, double investLongInterest,
                  double investShortInterest) {
        this.name = name;
        this.fId = fId;
        this.loanInterest = loanInterest;
        this.investLongInterest = investLongInterest;
        this.investShortInterest = investShortInterest;
    }
}
