package com.circleescape.server.model;

import lombok.Value;

import static com.circleescape.server.model.MathUtils.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/***
 * immutable
 * @param r
 *
 */
@Value
public class PolarCoordinates {

	@NotNull
	@Min(0)
	private double r;
	
	@NotNull
	private double theta;
	
	/**
	 * Negative r throws illegal arguments exception
	 * Theta will be normalized s.t. 0 <= theta < TAU
	 * @param r
	 * @param theta
	 */
	public PolarCoordinates(double r, double theta) {
		if (r<0)
			throw new IllegalArgumentException("Radius can't be negative.");
		
		this.r = r;
		
		if ( theta < 0 ) {
			theta = TAU + theta % TAU;
		} else if ( TAU < theta ) {
			theta = theta % TAU;
		}
		this.theta = theta;		
	}
	
	public PolarCoordinates normalize(double r) {
		
		double normalizedR = Math.min(this.r, r);
		
		return new PolarCoordinates(normalizedR, theta);
	}
	
	public double distance(PolarCoordinates pc) {
		return Math.sqrt(Math.pow(r, 2) + Math.pow(pc.r, 2) - 2 * r * pc.r * Math.cos(theta - pc.theta));
	};
	
	public PolarCoordinates add(PolarCoordinates polarDiff) {
		return fromCartesian(toCartesian().add(polarDiff.toCartesian()));
	}
	
	public Vector2D toCartesian() {
		return new Vector2D(Math.cos( theta ) * r, 
		                    Math.sin( theta ) * r); 
	}
	
	private PolarCoordinates fromCartesian(Vector2D v) {
		double r_ = Math.sqrt( v.getX() * v.getX() + v.getY() * v.getY() );
		double theta_ = Math.atan2(v.getY(), v.getX());
		
		return new PolarCoordinates(r_, theta_);
	}
}
