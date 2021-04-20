package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezes


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentFreezesBinding
import com.thepyprogrammer.ktlib.atHour
import com.thepyprogrammer.ktlib.onDate
import java.io.File
import java.io.FileReader
import java.time.*

class FreezesFragment : Fragment() {
    private lateinit var temp: FragmentFreezesBinding
    private val dateData = arrayListOf<LocalDateTime>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        temp = FragmentFreezesBinding.inflate(inflater, container, false)

        val freezesFile = File(activity?.filesDir, "freezes.txt")
        if(!freezesFile.exists()) freezesFile.createNewFile()

        with(FileReader(freezesFile)) {
            forEachLine {
                if(it!="") {
                    val timeInMillis = it.trim().toLong()
                    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMillis), ZoneId.systemDefault())
                    dateData.add(dateTime)
                }
            }
        }

        temp.calendarView.maxDate = System.currentTimeMillis()

        temp.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val dateNow = LocalDate.of(year, month+1, dayOfMonth)
            // For some reason month ranges from 0 to 11

            temp.barChart.apply {
                resetChart(this)
                val data = BarData()
                val points = mutableListOf<BarEntry>()

                val filteredDateData = dateData.filter { it.onDate(dateNow) }

                for(hour in 0..24) {
                    val hourFilteredDateData = filteredDateData.filter { it.atHour(hour) }
                    val freezesNow = hourFilteredDateData.size.toFloat()
                    points.add(BarEntry(hour.toFloat(), freezesNow))
                }

                data.addDataSet(BarDataSet(points, "Freeze Events").apply { color = resources.getColor(R.color.primary) })
                animateY(500)

                visibility = View.VISIBLE
                this.data = data
            }
        }

        temp.barChart.apply {

            xAxis.setDrawLabels(true)
            xAxis.setDrawAxisLine(true)
            xAxis.axisMinimum = 0f
            xAxis.axisMaximum = 24f
            xAxis.axisLineColor = Color.WHITE
            xAxis.gridColor = resources.getColor(R.color.primary)
            xAxis.textColor = Color.WHITE
            axisLeft.setLabelCount(6, false)
            axisLeft.setDrawLabels(true)
            axisLeft.setDrawAxisLine(true)
            axisLeft.axisLineColor = Color.WHITE
            axisLeft.gridColor = resources.getColor(R.color.primary)
            axisLeft.textColor = Color.WHITE
            axisLeft.axisMinimum = 0f
            axisLeft.axisMaximum = 60f

            legend.textColor = Color.WHITE

            axisRight.isEnabled = false
            description.text = ""

            visibility = View.GONE
        }

        return temp.root
    }

    private fun resetChart(barChart : BarChart) {
        barChart.fitScreen()
        barChart.data?.clearValues()
        barChart.notifyDataSetChanged()
        barChart.clear()
        barChart.invalidate()
    }
}