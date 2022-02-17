package com.circleescape.server.api.game;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.circleescape.server.model.*;
import com.circleescape.server.scoreboard.data.Score;
import com.circleescape.server.scoreboard.data.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.circleescape.server.api.game.model.GameState;

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

	private final GameService gameService;

	@Autowired
	public CircleEscapeController(GameService gameService) {
		this.gameService = gameService;
	}


	@Operation(summary = "Start a new game, return game state", 
			   responses = {
		      @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameState.class)))
		       })

	@PostMapping("/newGame")
	public ResponseEntity<GameState> startNewGameVanilla() {
		GameParameters gp = new GameParameters(100, 3.0);

		gameService.createNewGame();
		return ResponseEntity.ok().build();

		//game.initialize(gp);
		//GameState gs = game.getGameState();
		//return new ResponseEntity<>(gs, HttpStatus.OK);
	}
	
	//@PostMapping("/newCustomGame")
	//public ResponseEntity<GameState> startNewGameCustom(@PathVariable("pondRadius") double radius,
	//		                                      @PathVariable("speedFactor") Double speedFactor) {
	//	GameParameters gp = new GameParameters(radius, speedFactor);
	//	game.initialize(gp);
	//	return new ResponseEntity<>(game.getGameState(), HttpStatus.OK);
	//}
	

	@PostMapping("/planPolarSession")
	public ResponseEntity<TurnResponse> planStepPolarSession(
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			@RequestBody
			@Valid final PairGeneric<PolarCoordinates> pair) {
		return ResponseEntity.ok().build();
//		return stepHelper(pair, false);
	}
	
	@PostMapping("/stepPolar")
	public ResponseEntity<TurnResponse> makeStepPolarSession(
			@RequestParam(defaultValue = "false") boolean dryrun,
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			@RequestBody
			@Valid final PairGeneric<PolarCoordinates> pair) {
		return ResponseEntity.ok().build();
		//return stepHelper(pair, true);
	}
	
/*	public ResponseEntity<TurnResponse> stepHelper(PairGeneric<PolarCoordinates> pair, boolean persist) {
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
	}*/

}
