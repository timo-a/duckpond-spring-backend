package com.circleescape.server.model

import com.circleescape.server.api.game.model.GameStatus
import org.springframework.beans.factory.annotation.Autowired
import com.circleescape.server.model.game.persistence.Collocation
import com.circleescape.server.model.game.persistence.GameEntity
import com.circleescape.server.model.game.persistence.GameEntityRepository
import com.circleescape.server.model.game.persistence.GameParameters
import org.springframework.stereotype.Service
import kotlin.Pair

@Service
class GameService @Autowired constructor(private val gameEntityRepository: GameEntityRepository) {

    fun createNewGame(pondradius:Double=1.0, speedfactor:Double=4.0): Game {
        val c = Collocation(0.0, 0.0, 0.0)
        val gp = GameParameters(speedfactor, pondradius)
        val game = GameEntity("secret", gp, listOf(c), GameStatus.ONGOING.toString())
        return gameEntityRepository.save(game).toGame()
    }

    fun gameExists(id: Int) = gameEntityRepository.existsById(id.toLong())

    /**
     * this function assumes that the entry in the db exists
     */
    fun getNumberOfTurns(id: Int): Int {
        return gameEntityRepository.getById(id.toLong()).collocations.size
    }

    fun getGame(id: Int): Game? {
        val gameEntity = gameEntityRepository.findById(id.toLong())
        return if (gameEntity.isPresent) {
            gameEntity.get().toGame()
        } else {
            null
        }
    }

    fun doStep(game: Game, duck: PolarCoordinates): Pair<Positions, GameStatus> {

        val stepCalculator = StepCalculator(GreedyGuard(), game.parameters)
        val pos = Positions(game.escapee, game.guard)

        return stepCalculator.step(pos, duck)//Pair(Collocation(0.0,0.0,0.0), GameStatus.ONGOING)
    }

    fun persist(game: Game, c: Positions, second: GameStatus) :Positions{
        return Positions(PolarCoordinates(c.escapee.r, c.escapee.theta), c.guard)

    }

    fun prepareResponse(c: Positions, second: GameStatus) : TurnResponse {
        return TurnResponse(
            duck = PolarCoordinates(c.escapee.r, c.escapee.theta),
            cat = c.guard,
            state = second
        )
    }


    fun GameEntity.toGame(): Game {
        val lastColloc = collocations.last()
        val duck = PolarCoordinates(
            lastColloc.duckRadius,
            lastColloc.duckAngle)

        return Game(
            speedFactor = gameParameters.speedFactor,
            radius = gameParameters.pondRadius,
            escapee = duck,
            guard = lastColloc.catAngle,
            state = GameStatus.valueOf(status)
        )
    }
}