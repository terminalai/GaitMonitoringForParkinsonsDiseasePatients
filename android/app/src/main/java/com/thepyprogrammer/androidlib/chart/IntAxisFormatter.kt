package com.thepyprogrammer.androidlib.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.thepyprogrammer.ktlib.string.removeRandomZeroes
import java.text.DecimalFormat
import kotlin.math.floor

open class IntAxisFormatter: ValueFormatter() {
    // override this for e.g. LineChart or ScatterChart
    override fun getPointLabel(entry: Entry?): String {
        return entry?.y?.toInt().toString().removeRandomZeroes()
    }
    // override this for BarChart
    override fun getBarLabel(barEntry: BarEntry?): String {
        return barEntry?.y?.toInt().toString().removeRandomZeroes()
    }
    // override this for custom formatting of XAxis or YAxis labels
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return floor(value.toDouble()).toInt().toString().removeRandomZeroes()
    }
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        //return value.toInt().toString().removeRandomZeroes()
        return (floor(value.toDouble()).toInt()).toString().removeRandomZeroes()
    }
}