package com.example.server2.actEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private UUID user;
    private boolean positive;
    private String type;
    private double amount;
    private Date start;

    public Action() {
    }

}
