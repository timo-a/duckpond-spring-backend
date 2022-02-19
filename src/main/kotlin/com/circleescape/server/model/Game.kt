package com.circleescape.server.model

import com.circleescape.server.api.game.model.GameStatus
import org.slf4j.LoggerFactory
import kotlin.math.abs

class Game constructor(val speedFactor: Double = 4.0,
           val radius: Double = 1.0,
           var escapee: PolarCoordinates,
           var guard: Double = 0.0,
           var state: GameStatus
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    private val guardStrategy: GuardStrategy = GreedyGuard()

    constructor(gp: GameParameters) : this(
        gp.speedFactor,
        gp.radius,
        PolarCoordinates(0.0, MathUtils.TAU / 4),
        MathUtils.TAU * 3 / 4,
        GameStatus.ONGOING
    )

    val parameters: GameParameters
        get() = GameParameters(radius, speedFactor)

    val gameState: GameState
        get() {
            val cat = Cat(guard)
            return GameState(speedFactor, radius, escapee, cat, state)
        }

    val positions: Positions
        get() = Positions(escapee, guard)

    fun moveEscapee(d: Diff?): Result? {
        return null
    }


}