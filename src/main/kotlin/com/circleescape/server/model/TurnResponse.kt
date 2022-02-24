package com.circleescape.server.model

import com.circleescape.server.api.game.model.GameStatus

class TurnResponse(
    val duck: PolarCoordinates,
    cat: Double,
    val state: GameStatus
) {
    val cat: Cat  = Cat(cat)
}
