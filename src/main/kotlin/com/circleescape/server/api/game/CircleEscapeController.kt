package com.circleescape.server.api.game

import com.circleescape.server.api.game.model.Duck
import com.circleescape.server.api.game.model.GameState
import com.circleescape.server.api.game.model.GameStatus
import com.circleescape.server.api.game.model.TurnResult
import com.circleescape.server.model.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import kotlin.math.sign

@CrossOrigin
@RestController
@Service
@RequestMapping("/game")
class CircleEscapeController @Autowired constructor(private val gameService: GameService) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Operation(description = "descrition",
        summary = "Start a new game, return game state",
        responses = [ApiResponse(
            responseCode = "200",
            description = "Successful Operation",
            content = arrayOf(Content(mediaType = "application/json", schema = Schema(implementation = GameState::class)))
        )]
    )

    @PostMapping("/newGame")
    fun startNewGameVanilla(): GameState {
        val gp = GameParameters(100.0, 3.0)
        return gameService.createNewGame().toGameState()
    }//todo return game id as well

    fun Game.toGameState() = GameState(
        speedFactor = speedFactor,
        pondRadius = radius,
        duck = escapee.toDuck(),
        catAngle = guard,
        state = state
    )
    fun PolarCoordinates.toDuck() = Duck(
        radius = r,
        angle = theta
    )

    //allow user to set the speed
    //@PostMapping("/newCustomGame")
    //public ResponseEntity<GameState> startNewGameCustom(@PathVariable("pondRadius") double radius,
    //		                                      @PathVariable("speedFactor") Double speedFactor) {
    //	GameParameters gp = new GameParameters(radius, speedFactor);
    //	game.initialize(gp);
    //	return new ResponseEntity<>(game.getGameState(), HttpStatus.OK);
    //}


    @PostMapping("/{gameId}/turn/{turnId}")
    fun makeStepPolar(
        @PathVariable(name = "gameId") gameId: Int,
        @PathVariable(name = "turnId") turnId: Int,
        @RequestParam(defaultValue = "false") dryrun: Boolean,
        @RequestBody
        @org.springframework.web.bind.annotation.RequestBody newDuckPosition: @Valid PolarCoordinates
    ): TurnResult {

        if (!gameService.gameExists(gameId))
            throw ResponseStatusException(HttpStatus.NOT_FOUND)


        val correctIndex = gameService.getNumberOfTurns(gameId)
        when(correctIndex.compareTo(turnId).sign) {
            -1 -> throw ResponseStatusException(HttpStatus.FORBIDDEN, "turnId too high, do turn $correctIndex first")
             1 -> throw ResponseStatusException(HttpStatus.FORBIDDEN, "this turn has already been recorded, ")
        }

        val game: Game = gameService.getGame(gameId)!!

        if (game.state != GameStatus.ONGOING) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "game already over")
        }

        var result: Pair<Positions, GameStatus> = gameService.doStep(game, newDuckPosition)



        if (!dryrun) gameService.persist(game, result.first, result.second)

        val response = gameService.prepareResponse(result.first, result.second)
        return response.toTurnResult()
    }

    fun TurnResponse.toTurnResult() = TurnResult(
        duckRadius = duck.r,
        duckAngle = duck.theta,
        catAngle = cat.theta,
        status = state
    )

}