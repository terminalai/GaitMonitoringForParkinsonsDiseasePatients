package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezes


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentFreezesBinding
import com.thepyprogrammer.ktlib.atHour
import com.thepyprogrammer.ktlib.onDate
import com.thepyprogrammer.ktlib.toLocalDateTime
import java.io.File
import java.io.FileReader
import java.time.LocalDate
import java.time.LocalDateTime

class FreezesFragment : Fragment() {
    private lateinit var temp: FragmentFreezesBinding
    private val dateData = mutableListOf<LocalDateTime>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        temp = FragmentFreezesBinding.inflate(inflater, container, false)

        val freezesFile = File(activity?.filesDir, "freezes.txt")
        if (!freezesFile.exists()) freezesFile.createNewFile()

        with(FileReader(freezesFile)) {
            forEachLine {
                if (it.isNotEmpty()) {
                    val timeInMillis = it.trim().toLong()
                    val dateTime = timeInMillis.toLocalDateTime()
                    dateData.add(dateTime)
                }
            }
        }

        dateData.sort()

        temp.calendarView.maxDate = System.currentTimeMillis()

        temp.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val dateNow = LocalDate.of(year, month + 1, dayOfMonth)
            // For some reason month ranges from 0 to 11
            dateChange(dateNow)

        }

        with(temp.barChart) {
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawLabels(true)
                setDrawAxisLine(true)
                setDrawGridLines(false)
                axisMinimum = 0f
                axisMaximum = 24f
            }

            axisLeft.apply {
                setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
                setLabelCount(6, false)
                setDrawLabels(true)
                setDrawAxisLine(true)
                setDrawGridLines(false)
                axisMinimum = 0f
                axisMaximum = 60f
            }

            legend.textColor = Color.WHITE

            axisRight.isEnabled = false
            description.text = ""

            visibility = View.GONE
            dateChange(LocalDate.now())
        }

        return temp.root
    }

    private fun dateChange(dateNow: LocalDate) {
        temp.barChart.apply {
            resetChart(this)
            val data = BarData()
            val points = mutableListOf<BarEntry>()

            val filteredDateData = dateData.filter {
                it.onDate(dateNow)
            }

            for (hour in 0..24) {
                val freezesNow = filteredDateData.filter {
                    it.atHour(hour)
                }.size.toFloat()
                points.add(
                    BarEntry(
                        hour.toFloat(),
                        freezesNow
                    )
                )
            }

            data.addDataSet(
                BarDataSet(points, "Freeze Events").also {
                    it.color = R.color.primary
                }
            )
            animateY(500)

            visibility = View.VISIBLE
            this.data = data
        }
    }

    private fun resetChart(barChart: BarChart) {
        barChart.fitScreen()
        barChart.data?.clearValues()
        barChart.notifyDataSetChanged()
        barChart.clear()
        barChart.invalidate()
    }
}