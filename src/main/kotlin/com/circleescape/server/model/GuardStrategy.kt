package com.circleescape.server.model

interface GuardStrategy {
    fun updateCat(
        parameters: GameParameters,
        currentCat: Double,
        currentDuck: PolarCoordinates,
        nextDuck: PolarCoordinates
    ): Double
}