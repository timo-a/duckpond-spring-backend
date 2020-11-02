package com.circleescape.server.model;

public interface GuardStrategy {

	public double updateCat(GameParameters parameters, double currentCat, PolarCoordinates currentDuck, PolarCoordinates nextDuck);
}
