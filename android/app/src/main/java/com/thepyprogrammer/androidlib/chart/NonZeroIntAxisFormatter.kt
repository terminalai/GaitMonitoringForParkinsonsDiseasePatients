package com.thepyprogrammer.androidlib.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.thepyprogrammer.ktlib.string.removeRandomZeroes
import kotlin.math.floor

class NonZeroIntAxisFormatter: IntAxisFormatter() {
    // override this for e.g. LineChart or ScatterChart
    override fun getPointLabel(entry: Entry?): String {
        return if(entry?.y == 0f) ""
        else entry?.y?.toInt().toString().removeRandomZeroes()
    }
    // override this for BarChart
    override fun getBarLabel(barEntry: BarEntry?): String {
        return if(barEntry?.y == 0f) ""
        else return barEntry?.y?.toInt().toString().removeRandomZeroes()
    }
    // override this for custom formatting of XAxis or YAxis labels
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return if(value == 0f) ""
        else return floor(value.toDouble()).toInt().toString().removeRandomZeroes()
    }
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return if(value == 0f) ""
        else super.getFormattedValue(value, axis)
    }
}