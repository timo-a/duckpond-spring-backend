package com.circleescape.server.model;

public class MathUtils {
	
	public static double TAU = 2 * Math.PI;

	public static double ϵ = 0.000001;
	
	public static double euclidianToAngle(double radius, double euclidian) {
		return euclidian / radius;
	}

}
