package com.circleescape.server.model;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameState {

	@NotNull
	private double speedFactor;
	@NotNull
	private double radius;

	@NotNull
	private PolarCoordinates duck;
	@NotNull
	private Cat cat;
	
	@NotNull
	private TurnResult state;

}
