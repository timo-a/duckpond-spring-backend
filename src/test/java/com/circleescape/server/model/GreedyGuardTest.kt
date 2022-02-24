package com.circleescape.server.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GreedyGuardTest {

    @Test
    fun test() {
        val gs: GuardStrategy = GreedyGuard()
        val parameters = GameParameters(100.0, 4.0)
        val nextDuck = gs.updateCat(
            parameters, MathUtils.TAU * 12 / 14, duck100(MathUtils.TAU * 13 / 14), duck100(
                MathUtils.TAU * 1 / 14
            )
        )
        println(MathUtils.TAU * 1 / 14)
        println(nextDuck)
        Assertions.assertTrue(Math.abs(nextDuck - MathUtils.TAU * 1 / 14) < MathUtils.ϵ)
    }

    private fun duck100(rad: Double): PolarCoordinates {
        return PolarCoordinates(100.0, rad)
    }
}