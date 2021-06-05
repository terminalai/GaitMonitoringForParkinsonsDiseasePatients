package com.thepyprogrammer.ktlib.math.types

data class Angle(val angle: Double) {
    infix operator fun plus(x: Angle) = Angle(angle + x.angle)
    infix operator fun plus(x: Double) = Angle(angle + x)
    infix operator fun plus(x: Float) = Angle(angle + x.toDouble())
    infix operator fun plus(x: Int) = Angle(angle + x)

    infix operator fun minus(x: Angle) = Angle(angle - x.angle)
    infix operator fun minus(x: Double) = Angle(angle - x)
    infix operator fun minus(x: Float) = Angle(angle - x.toDouble())
    infix operator fun minus(x: Int) = Angle(angle - x)
}