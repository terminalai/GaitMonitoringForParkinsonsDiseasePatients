package com.thepyprogrammer.ktlib.math.types

import kotlin.math.*

data class Complex(val re: Double, val im: Double = 0.0, val isUnit: Boolean = false) {
    constructor(value: Complex): this(value.re, value.im, value.isUnit)

    constructor(mag: Double, arg: Angle) : this(cos(arg.angle) * mag, sin(arg.angle) * mag, mag == 1.0)

    infix operator fun plus(x: Complex) = Complex(re + x.re, im + x.im)
    infix operator fun plus(x: Double) = Complex(re + x, im)
    infix operator fun plus(x: Float) = Complex(re + x.toDouble(), im)
    infix operator fun plus(x: Int) = Complex(re + x, im)


    infix operator fun minus(x: Complex) = Complex(re - x.re, im - x.im)
    infix operator fun minus(x: Double) = Complex(re - x, im)
    infix operator fun minus(x: Float) = Complex(re - x.toDouble(), im)
    infix operator fun minus(x: Int) = Complex(re - x, im)


    infix operator fun times(x: Double) = Complex(re * x, im * x)
    infix operator fun times(x: Int) = Complex(re * x, im * x)
    infix operator fun times(x: Float) = times(x.toDouble())
    infix operator fun times(x: Complex) = Complex(re * x.re - im * x.im, re * x.im + im * x.re)


    infix operator fun div(x: Double) = Complex(re / x, im / x)
    infix operator fun div(x: Float) = div(x.toDouble())
    infix operator fun div(x: Int) = Complex(re / x, im / x)
    infix operator fun div(x: Complex) = Complex(mag / x.mag, arg - x.arg)



    val exp by lazy {
        Complex(
            cos(im), sin(im)
        ) * (
            cosh(re) + sinh(re)
        )
    }


    var timesConj = re * re + im * im


    var mag = sqrt(timesConj)


    var unit = if (!isUnit) if (mag != 0.0) Complex(re / mag, im / mag, true) else Complex(0.0, 0.0, true)
    else this


    var alpha = abs(asin(unit.im))


    var arg = Angle(
        when {
            (im == 0.0 && re >= 0) -> 0.0
            (im == 0.0 && re < 0) -> com.thepyprogrammer.ktlib.math.PI
            (re == 0.0 && im > 0) -> com.thepyprogrammer.ktlib.math.PI / 2
            (re == 0.0 && im < 0) -> -com.thepyprogrammer.ktlib.math.PI / 2
            (re > 0 && im > 0) -> alpha
            (re > 0 && im < 0) -> -alpha
            (re < 0 && im > 0) -> com.thepyprogrammer.ktlib.math.PI - alpha
            else -> alpha - com.thepyprogrammer.ktlib.math.PI
        }
    )


    override fun toString() = when {
        b == "0.000" -> a
        a == "0.000" -> b + 'i'
        im > 0 -> a + " + " + b + 'i'
        else -> a + " - " + b + 'i'
    }

    private val a = "%1.3f".format(re)
    private val b = "%1.3f".format(abs(im))
}

