package com.circleescape.server.scoreboard.data

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "SCORES")
class Score(
    @Column(name = "game") var gameId: Long,
    @Column(nullable = false) var name: String,
    @Column(nullable = false) var distance: Double,
    @Column var time: LocalDateTime,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)