package com.circleescape.server.model.game.persistence

import javax.persistence.Embeddable

@Embeddable
class GameParameters (
    var speedFactor: Double,
    var pondRadius: Double
)