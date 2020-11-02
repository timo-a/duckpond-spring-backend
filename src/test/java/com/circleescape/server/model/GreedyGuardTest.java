package com.circleescape.server.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GreedyGuardTest {

	@Test
	void test() {
		GuardStrategy gs = new GreedyGuard();
		GameParameters parameters = new GameParameters(100, 4);
		double nextDuck = gs.updateCat(parameters, MathUtils.TAU *12/14, duck100(MathUtils.TAU * 13/14), duck100(MathUtils.TAU * 1/14));

		System.out.println(MathUtils.TAU * 1/14);
		System.out.println(nextDuck);
		assertTrue(Math.abs(nextDuck - MathUtils.TAU * 1/14) < MathUtils.ϵ);
	}
	
	private PolarCoordinates duck100(double rad) {
		return new PolarCoordinates(100, rad);
	}

}
