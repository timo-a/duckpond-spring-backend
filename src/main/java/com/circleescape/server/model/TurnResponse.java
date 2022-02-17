package com.circleescape.server.model;

import javax.validation.constraints.NotNull;

import com.circleescape.server.api.game.model.TurnResult;
import lombok.Value;

@Value
public class TurnResponse {

	@NotNull
	PolarCoordinates duck;
	
	@NotNull
	Cat cat;
	
	@NotNull
	TurnResult state;
	
	public TurnResponse(PolarCoordinates duck, double cat, TurnResult state) {
		this.duck = duck;
		this.cat = new Cat(cat);
		this.state = state;
	}
}
