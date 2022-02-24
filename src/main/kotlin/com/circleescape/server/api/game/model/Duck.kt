package com.circleescape.server.api.game.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Coordinates of the duck")
class Duck (
    @field:Schema(description = "Distance from the centre of the pond")
    val radius: Double = 0.0,
    @field:Schema(description = "Angle of the Duck in radians (counterclockwise from 6 o'clock)",
        minimum = "0", maximum = "τ")
    val angle: Double = 0.0
)