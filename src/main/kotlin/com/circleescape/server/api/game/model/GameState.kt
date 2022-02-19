package com.circleescape.server.api.game.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "The state of the game")
class GameState(
    @field:Schema(
        defaultValue = "4",
        description = "How muh faster the cat is compared to the duck",
        example = "5.6",
        minimum = "1"
    )
    val speedFactor: Double,

    @field:Schema(
        defaultValue = "1",
        description = "Radius of the Pond",
        example = "40",
        minimum = "1"
    )
    val radius: Double,
    val duck: Duck,
    val catAngle: Double,
    @field:Schema(description = "current state of the game")
    val state: GameStatus
)