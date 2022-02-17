package com.circleescape.server.model.game;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "GAMES")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private GameParameters gameParameters;

    @Column
    private String authKey;

    @ElementCollection
    private List<Collocation> collocations = new ArrayList<>();

}
