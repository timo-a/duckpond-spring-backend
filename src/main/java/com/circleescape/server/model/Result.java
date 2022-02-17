package com.circleescape.server.model;

import com.circleescape.server.api.game.model.TurnResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Result {

	@Getter
	private TurnResult state;
	@Getter
	private double newCatPosition;
}
