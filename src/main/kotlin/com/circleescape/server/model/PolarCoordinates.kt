package com.circleescape.server.model

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D
import javax.validation.constraints.Min
import kotlin.math.*

/***
 * immutable
 * @param r
 */
class PolarCoordinates(r: Double, theta: Double) {
    val r: @Min(0) Double
    val theta: Double

    /**
     * Negative r throws illegal arguments exception
     * Theta will be normalized s.t. 0 <= theta < TAU
     * @param r
     * @param theta
     */
    init {
        require(r >= 0) { "Radius can't be negative." }
        this.r = r
        var theta = theta % MathUtils.TAU
        if (theta < 0) {
            theta += MathUtils.TAU
        }
        this.theta = theta
    }

    fun normalize(r: Double): PolarCoordinates {
        val normalizedR = Math.min(this.r, r)
        return PolarCoordinates(normalizedR, theta)
    }

    fun distance(pc: PolarCoordinates): Double {
        return sqrt(r.pow(2.0) + pc.r.pow(2.0) - 2 * r * pc.r * cos(theta - pc.theta))
    }

    fun add(polarDiff: PolarCoordinates): PolarCoordinates {
        return fromCartesian(toCartesian().add(polarDiff.toCartesian()))
    }

    fun toCartesian(): Vector2D {
        return Vector2D(
            cos(theta) * r,
            sin(theta) * r
        )
    }

    private fun fromCartesian(v: Vector2D): PolarCoordinates {
        val r_ = sqrt(v.x * v.x + v.y * v.y)
        val theta_ = atan2(v.y, v.x)
        return PolarCoordinates(r_, theta_)
    }
}