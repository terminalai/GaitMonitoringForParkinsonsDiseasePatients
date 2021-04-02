package com.thepyprogrammer.gaitanalyzer.model.configurations

import kotlin.math.roundToInt

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

