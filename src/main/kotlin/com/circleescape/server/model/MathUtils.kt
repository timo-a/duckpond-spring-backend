package com.circleescape.server.model

object MathUtils {

    val TAU = 2 * Math.PI

    val ϵ = 0.000001

    fun euclidianToAngle(radius: Double, euclidian: Double): Double {
        return euclidian / radius
    }
}