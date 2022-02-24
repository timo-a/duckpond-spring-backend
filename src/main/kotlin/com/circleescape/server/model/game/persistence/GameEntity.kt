package com.circleescape.server.model.game.persistence

import javax.persistence.*

@Entity
@Table(name = "GAMES")
class GameEntity(
    var authKey: String, //TODO use it
    @Embedded var gameParameters: GameParameters,
    @ElementCollection var collocations: List<Collocation> = ArrayList(),
    var status: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)