package com.circleescape.server.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class GreedyGuard implements GuardStrategy {

	@Override
	public double updateCat(GameParameters parameters, double currentCat, 
			PolarCoordinates currentEscapeePosition, 
			PolarCoordinates nextEscapeePosition) {
		//TODO maybe clearer to first see if cat can reach theta_duck, if not compute closest point.

		log.debug("update cat");
		log.debug("duck goes from: " + currentEscapeePosition.getTheta());
		log.debug("duck goes   to: " + nextEscapeePosition.getTheta());
		double radius = parameters.getRadius();
		double escapeeDistance = nextEscapeePosition.distance(currentEscapeePosition);
		double catAngleScope = MathUtils.euclidianToAngle(radius, escapeeDistance * parameters.getSpeedFactor());

		if (nextEscapeePosition.getR() == 0 && currentEscapeePosition.getR() == 0 ) {
			return currentCat;
		}
		
		double angleDifferenceEscapeeGuard = nextEscapeePosition.getTheta() - currentCat;
		double absoluteDifference = Math.abs(angleDifferenceEscapeeGuard);
		double shortestAbsoluteDifference = absoluteDifference < MathUtils.TAU / 2 ? absoluteDifference : MathUtils.TAU - absoluteDifference; 
		double guardAngleTrip = Math.min(catAngleScope, shortestAbsoluteDifference);
		log.debug("angletrip: " + guardAngleTrip);
		if (angleDifferenceEscapeeGuard > 0) {// duck 5°, cat 355°        diff = -360
			log.debug("θ_duck > θ_cat");
			if (angleDifferenceEscapeeGuard < MathUtils.TAU / 2 ) {
				//increase θ is faster
				currentCat += guardAngleTrip;
			} else {
				currentCat -= guardAngleTrip;
				if (currentCat < 0) //carry over
					currentCat = MathUtils.TAU - currentCat;
			}
		} else {
			log.debug("θ_duck < θ_cat");
			if (-angleDifferenceEscapeeGuard < MathUtils.TAU / 2 ) {
				//increase angle
				log.debug("better to move back");				
				currentCat -= guardAngleTrip;  
			} else {
				log.debug("better to move forward");				
				currentCat += guardAngleTrip;
				log.debug("cat moves to: " + currentCat);				
				if (currentCat > MathUtils.TAU) {
					currentCat -= MathUtils.TAU;
					log.debug("cat corrected to: " + currentCat);				
				}
			}
			
		}
		return currentCat;
	}
	

}