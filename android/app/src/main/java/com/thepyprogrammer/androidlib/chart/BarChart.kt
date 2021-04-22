package com.thepyprogrammer.androidlib.chart

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis

fun formatBarChart(chart: BarChart, activity: AppCompatActivity) {
    val tfRegular = Typeface.createFromAsset(activity.assets, "fonts/OpenSans-Regular.ttf")
    val tfLight = Typeface.createFromAsset(activity.assets, "fonts/OpenSans-Light.ttf")

    val formatter = IntAxisFormatter()

    chart.apply {
        setDrawBarShadow(false)
        setDrawValueAboveBar(true)

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
            spaceTop = 15f;
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

//        marker = XYMarkerView(activity, formatter).also {
//            it.chartView = chart
//        }


        axisRight.isEnabled = false
        description.text = ""


    }

}