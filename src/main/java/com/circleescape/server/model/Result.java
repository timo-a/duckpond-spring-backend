package com.circleescape.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Result {

	@Getter
	private TurnResult state;
	@Getter
	private double newCatPosition;
}
