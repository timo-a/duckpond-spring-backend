package com.circleescape.server.model

import com.circleescape.server.api.game.model.GameStatus
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class StepCalculator(private val guardStrategy: GuardStrategy,
                     private val gameParameters: GameParameters) {

    /**
     * Assumes the game is still ongoing
     */
    fun step(positions: Positions, newDuck: PolarCoordinates): Pair<Positions, GameStatus> {
        assert(0 <= newDuck.theta)
        assert(newDuck.theta < MathUtils.TAU)
        assert(0 <= positions.escapee.theta)
        assert(positions.escapee.theta < MathUtils.TAU)
        assert(0 <= positions.guard)
        assert(positions.guard < MathUtils.TAU)

        val newDuck = newDuck.normalize(gameParameters.radius)
        val newGuard = guardStrategy.updateCat(gameParameters, positions.guard, positions.escapee, newDuck)
        assert(0 <= newGuard)
        assert(newGuard < MathUtils.TAU)


        val newPositions = Positions(newDuck, newGuard)

        val state : GameStatus = when {
            (newDuck.r > gameParameters.radius - 0.0000000001)
                    && catAtDuck(newGuard, newDuck.theta)
            ->  GameStatus.LOOSE
            (newDuck.r >= gameParameters.radius)
                    && !catAtDuck(newGuard, newDuck.theta)
            ->  GameStatus.WIN
            else -> GameStatus.ONGOING
        }

        return Pair(newPositions, state)

    }

    private fun duckAtTheEdge(duck: PolarCoordinates): Boolean {
        return abs(gameParameters.radius - duck.r) < MathUtils.ϵ
    }

    private fun catAtDuck(cat: Double, duck: Double): Boolean {
        return angleDiff(cat, duck) < MathUtils.ϵ
    }

    /**
     * returns the positive (minimal) difference between two angles in [0, tau)
     */
    private fun angleDiff(a: Double, b: Double): Double {
        assert(0 <= a)
        assert(a < MathUtils.TAU)
        assert(0 <= b)
        assert(b < MathUtils.TAU)
        val d = max(a,b) - min(a,b)
        assert(d >= 0)

        return if (d > Math.PI) MathUtils.TAU - d else d
    }
}