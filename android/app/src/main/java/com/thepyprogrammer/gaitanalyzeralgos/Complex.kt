package com.thepyprogrammer.gaitanalyzeralgos

import kotlin.math.*

data class Complex(val re: Double, val im: Double = 0.0, val isUnit:Boolean = false) {
    infix operator fun plus(x: Complex) = Complex(re + x.re, im + x.im)
    infix operator fun plus(x: Double) = Complex(re + x, im)
    infix operator fun plus(x: Int) = Complex(re + x, im)

    infix operator fun minus(x: Complex) = Complex(re - x.re, im - x.im)
    infix operator fun minus(x: Double) = Complex(re - x, im)
    infix operator fun minus(x: Int) = Complex(re - x, im)

    infix operator fun times(x: Double) = Complex(re * x, im * x)
    infix operator fun times(x: Int) = Complex(re * x, im * x)
    infix operator fun times(x: Complex) = Complex(re * x.re - im * x.im, re * x.im + im * x.re)

    infix operator fun div(x: Double) = Complex(re / x, im / x)


    val exp: Complex by lazy {
        Complex(cos(im), sin(im)) * (cosh(re) + sinh(
            re
        ))
    }

    var timesConj: Double = re * re + im * im
    var mag: Double = sqrt(timesConj)
    var unit: Complex
    var alpha: Double
    var arg: Double

    init {
        if(!isUnit) unit = if (mag != 0.0) Complex(re / mag, im / mag, true) else Complex(0.0, 0.0, true)
        else unit = this
        alpha = abs(asin(unit.im))
        arg = when {
            (im == 0.0 && re >= 0) -> 0.0
            (im == 0.0 && re < 0) -> PI
            (re == 0.0 && im > 0) -> PI / 2
            (re == 0.0 && im < 0) -> -PI / 2
            (re > 0 && im > 0) -> alpha
            (re > 0 && im < 0) -> -alpha
            (re < 0 && im > 0) -> PI - alpha
            else -> alpha - PI
        }
    }

    //val conj = com.thepyprogrammer.gaitanalyzeralgos.Complex(re, -im)


    override fun toString() = when {
        b == "0.000" -> a
        a == "0.000" -> b + 'i'
        im > 0 -> a + " + " + b + 'i'
        else -> a + " - " + b + 'i'
    }

    private val a = "%1.3f".format(re)
    private val b = "%1.3f".format(abs(im))
}

