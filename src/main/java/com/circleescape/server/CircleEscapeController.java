package com.circleescape.server;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.circleescape.server.scoreboard.data.Score;
import com.circleescape.server.scoreboard.data.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.circleescape.server.model.Game;
import com.circleescape.server.model.GameParameters;
import com.circleescape.server.model.GameState;
import com.circleescape.server.model.Pair;
import com.circleescape.server.model.PairGeneric;
import com.circleescape.server.model.PolarCoordinates;
import com.circleescape.server.model.SessionDB;
import com.circleescape.server.model.TurnResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import io.swagger.v3.oas.annotations.media.Schema;

@Log4j2
@CrossOrigin
@RestController
@Service
@RequestMapping("/game")
public class CircleEscapeController {
	
	
	@Resource(name = "getGame")
	Game game;

	@Resource(name = "getSessionDB")
	SessionDB sessionDB;

	private final ScoreRepository scoreRepository;

	@Autowired
	public CircleEscapeController(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}


	@Operation(summary = "Start a new game, return game state", 
			   responses = {
		      @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameState.class)))
		       })

	//@PostMapping("/newGame")
	public ResponseEntity<GameState> startNewGameVanilla() {
		GameParameters gp = new GameParameters(100, 3.0);
		game.initialize(gp);
		GameState gs = game.getGameState();
		return new ResponseEntity<>(gs, HttpStatus.OK);
	}
	
	//@PostMapping("/newCustomGame")
	public ResponseEntity<GameState> startNewGameCustom(@PathVariable("pondRadius") double radius,
			                                      @PathVariable("speedFactor") Double speedFactor) {
		GameParameters gp = new GameParameters(radius, speedFactor);
		game.initialize(gp);
		return new ResponseEntity<>(game.getGameState(), HttpStatus.OK);
	}
	
			
	@PostMapping("/newGameSession")
	public ResponseEntity<Pair> startNewGameVanillaSession() {
		Game game = new Game();
		GameParameters gp = new GameParameters(100, 4.0);
		game.initialize(gp);
		GameState gs = game.getGameState();
		int i = sessionDB.put(game);
		Pair pair = new Pair(i, gs);//TODO switch to pairgeneric
		return new ResponseEntity<>(pair, HttpStatus.OK);
	}
	
	@PostMapping("/planPolarSession")
	public ResponseEntity<TurnResponse> planStepPolarSession(@io.swagger.v3.oas.annotations.parameters.RequestBody
			@RequestBody
			@Valid final PairGeneric<PolarCoordinates> pair) {
		return stepHelper(pair, false);
	}
	
	@PostMapping("/stepPolarSession")
	public ResponseEntity<TurnResponse> makeStepPolarSession(@io.swagger.v3.oas.annotations.parameters.RequestBody
			@RequestBody
			@Valid final PairGeneric<PolarCoordinates> pair) {
		return stepHelper(pair, true);
	}
	
	public ResponseEntity<TurnResponse> stepHelper(PairGeneric<PolarCoordinates> pair, boolean persist) {
		log.info("is pair null? " + (pair==null));
		log.info("is sessionid null? " + (pair.getSessionID()==null));
		int sessionID = pair.getSessionID();
		PolarCoordinates diff = pair.getT();
		
		Optional<Game> oGame = sessionDB.getGame(sessionID);
		if(oGame.isPresent()) {
			Game game = oGame.get();
			GameState gs = game.getGameState();
			PolarCoordinates newDuck = gs.getDuck().add(diff);
			TurnResponse tr = persist ? game.updateEscapeePosition(newDuck)
			                          : game.predictGameState(newDuck);
			
			return new ResponseEntity<>(tr, HttpStatus.OK);
		} else {
			log.info("No such game present, return 404.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/")
	public String showRules() {
		return "Hi there";
	}

	@RequestMapping("/rules")
	public String showRules2() {
		return "Hi there";
	}



	@PostMapping("/{gameId}/postName")
	public ResponseEntity<Void> postNameOnScoreboard(
			@PathVariable long gameId,
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			@RequestBody String name) {
		boolean gameFinished = true; //TODO

		LocalDateTime timestamp = LocalDateTime.now();

		Score scoreBE = new Score(name);

		scoreRepository.save(scoreBE);

		return ResponseEntity.ok().build();
	}

}
