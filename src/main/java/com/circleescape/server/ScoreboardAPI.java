package com.circleescape.server;

import com.circleescape.server.scoreboard.data.Score;
import com.circleescape.server.scoreboard.data.ScoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@CrossOrigin
@RestController
@Service
public class ScoreboardAPI {

	private final ScoreRepository scoreRepository;

	@Autowired
	public ScoreboardAPI(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}

	@RequestMapping("/")
	public String showRules() {
		return "Hi there";
	}

	@RequestMapping("/rules")
	public String showRules2() {
		return "Hi there";
	}



	@PostMapping("/scoreboard/{gameId}/postName")
	public ResponseEntity<Void> postNameOnScoreboard(
			@PathVariable long gameId,
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			@RequestBody String name) {
		boolean gameFinished = true; //TODO

		LocalDateTime timestamp = LocalDateTime.now();

		Score scoreBE = new Score(gameId, name, 42, timestamp);

		scoreRepository.save(scoreBE);

		return ResponseEntity.ok().build();
	}

}
