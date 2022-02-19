package com.circleescape.server.model

object MathUtils {

    var TAU = 2 * Math.PI

    var ϵ = 0.000001

    fun euclidianToAngle(radius: Double, euclidian: Double): Double {
        return euclidian / radius
    }
}