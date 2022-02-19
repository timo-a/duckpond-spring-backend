package com.circleescape.server.model

import org.slf4j.LoggerFactory

class GreedyGuard : GuardStrategy {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun updateCat(
        parameters: GameParameters,
        currentCat: Double,
        currentEscapeePosition: PolarCoordinates,
        nextEscapeePosition: PolarCoordinates
    ): Double {
        //TODO maybe clearer to first see if cat can reach theta_duck, if not compute closest point.
        var currentCat = currentCat
        log.debug("update cat")
        log.debug("duck goes from: " + currentEscapeePosition.theta)
        log.debug("duck goes   to: " + nextEscapeePosition.theta)
        val radius = parameters.radius
        val escapeeDistance = nextEscapeePosition.distance(currentEscapeePosition)
        val catAngleScope = MathUtils.euclidianToAngle(radius, escapeeDistance * parameters.speedFactor)
        if (nextEscapeePosition.r == 0.0 && currentEscapeePosition.r == 0.0) {
            return currentCat
        }
        val angleDifferenceEscapeeGuard = nextEscapeePosition.theta - currentCat
        val absoluteDifference = Math.abs(angleDifferenceEscapeeGuard)
        val shortestAbsoluteDifference =
            if (absoluteDifference < MathUtils.TAU / 2) absoluteDifference else MathUtils.TAU - absoluteDifference
        val guardAngleTrip = Math.min(catAngleScope, shortestAbsoluteDifference)
        log.debug("angletrip: $guardAngleTrip")
        if (angleDifferenceEscapeeGuard > 0) { // duck 5°, cat 355°        diff = -360
            log.debug("θ_duck > θ_cat")
            if (angleDifferenceEscapeeGuard < MathUtils.TAU / 2) {
                //increase θ is faster
                currentCat += guardAngleTrip
            } else {
                currentCat -= guardAngleTrip
                if (currentCat < 0) //carry over
                    currentCat = MathUtils.TAU - currentCat
            }
        } else {
            log.debug("θ_duck < θ_cat")
            if (-angleDifferenceEscapeeGuard < MathUtils.TAU / 2) {
                //increase angle
                log.debug("better to move back")
                currentCat -= guardAngleTrip
            } else {
                log.debug("better to move forward")
                currentCat += guardAngleTrip
                log.debug("cat moves to: $currentCat")
                if (currentCat > MathUtils.TAU) {
                    currentCat -= MathUtils.TAU
                    log.debug("cat corrected to: $currentCat")
                }
            }
        }
        return currentCat
    }
}