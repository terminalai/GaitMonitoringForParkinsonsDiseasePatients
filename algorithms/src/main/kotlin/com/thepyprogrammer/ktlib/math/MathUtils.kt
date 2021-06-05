package com.thepyprogrammer.ktlib.math

import java.lang.Math.getExponent
import java.util.*
import kotlin.math.ln1p
import kotlin.math.sign
import kotlin.math.roundToInt

var PI = Math.PI
var E = java.lang.Math.E





fun Float.round(dp: Int = 0) = run {
    when {
        dp <= 0 -> this.roundToInt().toFloat()
        else -> {
            String.format("%.${dp}f", this).toFloat()
        }
    }
}

fun Double.round(dp: Int = 0) = run {
    when {
        dp <= 0 -> this.roundToInt().toDouble()
        else -> {
            String.format("%.${dp}f", this).toDouble()
        }
    }
}

fun sin(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.sin(angles[i])
    return arr
}

fun sin(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.sin(angle))
    return arr
}

fun cos(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.cos(angles[i])
    return arr
}

fun cos(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.cos(angle))
    return arr
}

fun tan(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.tan(angles[i])
    return arr
}

fun tan(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.tan(angle))
    return arr
}


fun asin(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.asin(angles[i])
    return arr
}

fun asin(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.asin(angle))
    return arr
}


fun acos(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.acos(angles[i])
    return arr
}

fun acos(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.acos(angle))
    return arr
}


fun atan(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.atan(angles[i])
    return arr
}

fun atan(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.atan(angle))
    return arr
}


fun sinh(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.sinh(angles[i])
    return arr
}

fun sinh(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.sinh(angle))
    return arr
}


fun cosh(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.cosh(angles[i])
    return arr
}

fun cosh(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.cosh(angle))
    return arr
}


fun tanh(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = kotlin.math.tanh(angles[i])
    return arr
}

fun tanh(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(kotlin.math.tanh(angle))
    return arr
}


fun sqrt(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.sqrt(values[i])
    return arr
}

fun sqrt(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.sqrt(value))
    return arr
}

fun cbrt(value: Double) = Math.cbrt(value)


fun cbrt(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = cbrt(values[i])
    return arr
}

fun cbrt(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(cbrt(value))
    return arr
}


fun log(vararg values: Double, base: Double = 10.0): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.log(values[i], base)
    return arr
}

fun log(values: Collection<Double>, base: Double = 10.0): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.log(value, base))
    return arr
}


fun log10(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.log10(values[i])
    return arr
}

fun log10(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.log10(value))
    return arr
}


fun log1p(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = ln1p(values[i])
    return arr
}

fun log1p(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(ln1p(value))
    return arr
}


fun exp(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.exp(values[i])
    return arr
}

fun exp(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.exp(value))
    return arr
}


fun expm1(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.expm1(values[i])
    return arr
}

fun expm1(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.expm1(value))
    return arr
}


fun toRadians(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = Math.toRadians(angles[i])
    return arr
}

fun toRadians(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(Math.toRadians(angle))
    return arr
}


fun toDegrees(vararg angles: Double): DoubleArray {
    val arr = DoubleArray(angles.size)
    for (i in angles.indices) arr[i] = Math.toDegrees(angles[i])
    return arr
}

fun toDegrees(angles: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (angle in angles) arr.add(Math.toDegrees(angle))
    return arr
}


fun sign(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = sign(values[i])
    return arr
}

fun sign(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(sign(value))
    return arr
}


fun ceil(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.ceil(values[i])
    return arr
}

fun ceil(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.ceil(value))
    return arr
}

fun floor(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.floor(values[i])
    return arr
}

fun floor(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.floor(value))
    return arr
}


fun round(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.round(values[i])
    return arr
}

fun round(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.round(value))
    return arr
}


fun rint(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = kotlin.math.round(values[i])
    return arr
}

fun rint(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(kotlin.math.round(value))
    return arr
}


fun ulp(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = Math.ulp(values[i])
    return arr
}

fun ulp(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(Math.ulp(value))
    return arr
}


fun getExponent(vararg values: Double): DoubleArray {
    val arr = DoubleArray(values.size)
    for (i in values.indices) arr[i] = getExponent(values[i]).toDouble()
    return arr
}

fun getExponent(values: Collection<Double>): Collection<Double> {
    val arr: ArrayList<Double> = ArrayList()
    for (value in values) arr.add(getExponent(value).toDouble())
    return arr
}

fun max(vararg values: Number): Double {
    var maxn = Double.NEGATIVE_INFINITY
    for (value in values) {
        if (value.toDouble() > maxn) maxn = value.toDouble()
    }
    return maxn
}

fun max(values: Collection<Number>): Double {
    var maxn = Double.NEGATIVE_INFINITY
    for (value in values) {
        if (value.toDouble() > maxn) maxn = value.toDouble()
    }
    return maxn
}

fun fib(upperBound: Double): IntArray {
    var a = 0
    var b = 1
    var temp: Int
    val arr: ArrayList<Int> = ArrayList()
    while (a < upperBound) {
        arr.add(a)
        temp = b
        b += a
        a = temp
    }
    val array = IntArray(arr.size)
    for (i in 0 until arr.size) array[i] = arr[i]
    return array
}


// Random
val random = Random()
fun randInt() = random.nextInt()
fun randInt(upperBound: Int) = random.nextInt(upperBound)
fun randInt(lowerBound: Int, upperBound: Int) = lowerBound + randInt(upperBound - lowerBound)
fun randInt(lowerBound: Int, upperBound: Int, step: Int) = lowerBound + step * randInt((upperBound - lowerBound - 1) / step - 1)

fun randDouble() = random.nextDouble()
fun randDouble(upperBound: Double) = Math.random() * upperBound
fun randDouble(lowerBound: Double, upperBound: Double) = lowerBound + randDouble(upperBound - lowerBound)
fun randDouble(lowerBound: Double, upperBound: Double, step: Double) = lowerBound + Math.random() * ((upperBound - lowerBound - 1) / step - 1)

fun randFloat() = random.nextDouble()
fun randFloat(upperBound: Float) = Math.random() * upperBound
fun randFloat(lowerBound: Float, upperBound: Float) = lowerBound + randFloat(upperBound - lowerBound)
fun randFloat(lowerBound: Float, upperBound: Float, step: Float) = lowerBound + randFloat((upperBound - lowerBound - 1) / step - 1)

fun randBoolean() = random.nextBoolean()