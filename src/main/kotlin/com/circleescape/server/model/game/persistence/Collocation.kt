package com.circleescape.server.model.game.persistence

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Collocation( //todo embed the duck?
    @Column(nullable = false) var duckRadius: Double = 0.0,
    @Column(nullable = false) var duckAngle: Double = 0.0,
    @Column(nullable = false) var catAngle: Double = 0.0
  /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;*/
)