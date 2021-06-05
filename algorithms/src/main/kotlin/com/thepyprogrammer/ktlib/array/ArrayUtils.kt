package com.thepyprogrammer.ktlib.array

import com.thepyprogrammer.ktlib.math.types.Complex

/**
 * Functions for Double and Complex Arrays
 */

/**
 * Compute X * conj(X)
 */
fun Array<Complex>.timesConj(): Array<Double> = run {
    val newList = mutableListOf<Double>()
    this.forEach {
        newList.add(it.timesConj)
    }
    newList.toTypedArray()
}

/**
 * Piecewise Division for Double Array
 */
infix operator fun Array<Double>.div(x: Number) = run {
    val newList = mutableListOf<Double>()
    this.forEach {
        newList.add(it / x.toDouble())
    }
    newList.toTypedArray()
}

/**
 * Slice (with start, end and stop) for Double Array
 */
fun Array<Any>.slice(start: Int, end: Int, step: Int = 1) = run {
    val newList = mutableListOf<Any>()
    for (i in start..end step step) {
        newList.add(this[i])
    }
    newList.toTypedArray()
}

/**
 * Find sum of all elements in Double Array
 */
fun Array<Double>.sum() = run {
    var sum = 0.0
    this.forEach {
        sum += it
    }
    sum
}

/**
 * Find mean of Double Array
 */
fun Array<Double>.mean() = sum() / size

/**
 * Normalise Double Array
 */
fun Array<Double>.normalise() = run {
    val newList = mutableListOf<Double>()
    this.forEach {
        newList.add(it - mean())
    }
    newList.toTypedArray()
}

/**
 * Convert Double Array to generic Complex Array
 */
fun Array<Double>.toComplex(): Array<Complex> = run {
    val newList = mutableListOf<Complex>()
    this.forEach {
        newList.add(Complex(it))
    }
    newList.toTypedArray()
}

/**
 * Get a 1D x-sized Zero Double Array
 */
fun zeros(x: Int) = run {
    val newList = mutableListOf<Double>()
    for (i in 0..x) {
        newList.add(0.0)
    }
    newList.toTypedArray()
}

/**
 * Get a 2D row x col Zero Double Array
 */
fun zeros(row: Int, col: Int): Array<Array<Double>> = run {
    val newList = mutableListOf<Array<Double>>()
    for (i in 0..row) {
        newList.add(zeros(col))
    }
    newList.toTypedArray()
}

/**
 * Transpose 2D Double Array
 */
fun Array<Array<Double>>.transpose() = run {
    val numRow = this.size
    val numCol = this[0].size
    val array = zeros(numCol, numRow)

    for (i in 0..numRow) {
        for (j in 0..numCol) {
            array[j][i] = this[i][j]
        }
    }
    array
}

/**
 * Convert FloatArray to Vector
 */
fun FloatArray.toVector() = run {
    Vector(get(0).toDouble(), get(1).toDouble(), get(2).toDouble())
}


