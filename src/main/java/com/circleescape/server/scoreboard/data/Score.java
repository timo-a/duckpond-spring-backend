package com.circleescape.server.scoreboard.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SCORES")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game")
    private Long gameId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double distance;

    @Column
    private LocalDateTime time;

    public Score(Long gameId, String name, double distance, LocalDateTime time) {
        this.gameId = gameId;
        this.name = name;
        this.distance = distance;
        this.time = time;
    }

}
