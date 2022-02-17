package com.circleescape.server.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Collocation {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;*/

    @Column(nullable = false)
    private double duckRadius;//todo embed the duck?
    @Column(nullable = false)
    private double duckAngle;
    @Column(nullable = false)
    private double catAngle;
}
