package com.circleescape.server.scoreboard.data;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "SCORES")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "game")
    private Long gameId;

    public void setGameId(Long id) {
        this.gameId = id;
    }

    public Long getGameId() {
        return gameId;
    }

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double distance;
    @Column
    private LocalDateTime time;

    public Score() {}

    public Score(Long gameId, String name, double distance, LocalDateTime time) {
        this.gameId = gameId;
        this.name = name;
        this.distance = distance;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
