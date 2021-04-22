package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.thepyprogrammer.androidlib.chart.IntAxisFormatter
import com.thepyprogrammer.androidlib.chart.NonZeroIntAxisFormatter
import com.thepyprogrammer.androidlib.chart.formatBarChart
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentSelectedBinding
import com.thepyprogrammer.ktlib.atHour
import com.thepyprogrammer.ktlib.onDate
import java.time.LocalDateTime

class SelectedFragment : Fragment() {
    private lateinit var binding: FragmentSelectedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val completeViewModel =
            ViewModelProvider(requireActivity()).get(CompleteViewModel::class.java)
        val dateData =
                if(completeViewModel.dateData.value != null)
                    completeViewModel.dateData.value!!
                else mutableListOf()
        val filteredDateData = dateData.filter {
            completeViewModel.dateSelected.value?.let { it1 -> it.onDate(it1) } == true
        }
        val data = BarData()
        val points = mutableListOf<BarEntry>()

        var maxFreeze = 0f
        for (hour in 0..24) {
            val freezesNow = filteredDateData.filter {
                it.atHour(hour)
            }.size.toFloat()
            if (freezesNow > maxFreeze) maxFreeze = freezesNow

            points.add(
                BarEntry(
                    hour.toFloat(),
                        freezesNow
                )
            )
        }

        data.addDataSet(
            BarDataSet(points, "Freeze Events").apply {
                color = R.color.primary
                valueFormatter = NonZeroIntAxisFormatter()
                valueTypeface = Typeface.createFromAsset((activity as AppCompatActivity).assets, "fonts/OpenSans-Light.ttf")
                valueTextSize = 12f

            }
        )


        formatBarChart(binding.barChart, activity as AppCompatActivity)

        binding.barChart.apply {
            axisLeft.axisMaximum = maxFreeze + 2
            this.data = data
            animateY(1000)
        }

    }
}