package com.circleescape.server.api.game.model

data class TurnResult(
    val duckRadius: Double,
    val duckAngle: Double,
    val catAngle: Double,
    val status: GameStatus)
