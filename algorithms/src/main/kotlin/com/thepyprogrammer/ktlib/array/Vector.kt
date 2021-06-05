package com.thepyprogrammer.ktlib.array

data class Vector(
    var x: Double = 0.0,
    var y: Double = 0.0,
    var z: Double = 0.0
) {
    override fun toString() = "($x, $y, $z)"
}