package com.circleescape.server.model

import com.circleescape.server.api.game.model.GameStatus

class GameState(val speedFactor: Double,
                val pondRadius: Double,
                val duck: PolarCoordinates,
                val cat: Cat,
                val state: GameStatus
)