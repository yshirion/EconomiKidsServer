package com.example.server2.actEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    UUID user;
    boolean positive;
    String type;
    double amount;
    Date start;

    public Action() {
    }

}
