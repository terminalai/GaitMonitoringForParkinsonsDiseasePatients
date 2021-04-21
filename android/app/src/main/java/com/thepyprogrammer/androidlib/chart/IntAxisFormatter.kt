package com.thepyprogrammer.androidlib.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import com.thepyprogrammer.ktlib.string.removeRandomZeroes

class IntAxisFormatter: ValueFormatter() {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return value.toInt().toString().removeRandomZeroes()
    }
}