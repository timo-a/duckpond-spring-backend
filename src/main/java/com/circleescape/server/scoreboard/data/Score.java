package com.circleescape.server.scoreboard.data;

import javax.persistence.*;

@Entity
@Table(name = "SCORES")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.TABLE)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    public Score() {}

    public Score(String name) {
        this.name = name;
    }

}
