package com.circleescape.server;

import com.circleescape.server.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@CrossOrigin
@RestController
@Service
@RequestMapping("/gamesession")
public class CircleEscapeSessionController {


	@Resource(name = "getGame")
	Game game;

	@Resource(name = "getSessionDB")
	SessionDB sessionDB;

	@Autowired
	public CircleEscapeSessionController() {
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

}
