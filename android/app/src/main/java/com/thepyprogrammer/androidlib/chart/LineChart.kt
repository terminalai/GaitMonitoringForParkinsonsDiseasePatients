package com.thepyprogrammer.androidlib.chart

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData

fun formatLineChart(chart: LineChart, activity: AppCompatActivity) {
    val tfRegular = Typeface.createFromAsset(activity.assets, "fonts/OpenSans-Regular.ttf")
    val tfLight = Typeface.createFromAsset(activity.assets, "fonts/OpenSans-Light.ttf")

    val formatter = IntAxisFormatter()


    chart.apply {

        description.isEnabled = true
        setTouchEnabled(true)
        dragDecelerationFrictionCoef = 0.9f


        isDragEnabled = true
        setScaleEnabled(true)
        setDrawGridBackground(false)
        isHighlightPerDragEnabled = true

        data = LineData()
        data.setValueTextColor(Color.WHITE)


        setBackgroundColor(android.graphics.Color.WHITE)

        setViewPortOffsets(0f, 0f, 0f, 0f)

        legend.isEnabled = false

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            typeface = tfLight
            setDrawLabels(true)
            setDrawAxisLine(true)
            setDrawGridLines(false)
            axisMinimum = 0f
            axisMaximum = 24f
            granularity = 1f
            isGranularityEnabled = true
            valueFormatter = formatter
            textSize = 12f

        }

        axisLeft.apply {
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            typeface = tfLight
            setDrawLabels(true)
            setDrawAxisLine(true)
            setDrawGridLines(false)
            spaceTop = 15f
            axisMinimum = -170f
            axisMaximum = 170f
            axisMinimum = 0f
            axisMaximum = 60f
            granularity = 1f
            isGranularityEnabled = true
            valueFormatter = formatter
            textSize = 12f
        }

        legend.apply {
            typeface = tfLight
            textSize = 12f
        }

        setVisibleXRangeMaximum(12f)
        isScaleXEnabled = true

        axisRight.isEnabled = false
        description.text = ""


    }

}