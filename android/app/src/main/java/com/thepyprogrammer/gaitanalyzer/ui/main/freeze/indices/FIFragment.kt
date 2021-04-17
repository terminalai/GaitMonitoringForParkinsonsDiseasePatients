package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.indices

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentFiBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class FIFragment : Fragment() {

    private lateinit var binding: FragmentFiBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiBinding.inflate(inflater, container, false)
        val root = binding.root

        with(binding.fiChart) {
            description.isEnabled = true
            setTouchEnabled(true)
            dragDecelerationFrictionCoef = 0.9f


            isDragEnabled = true
            setScaleEnabled(true)
            setDrawGridBackground(false)
            isHighlightPerDragEnabled = true


            setBackgroundColor(Color.WHITE)

            setViewPortOffsets(0f, 0f, 0f, 0f)

            legend.isEnabled = false


            xAxis.apply {
                position = XAxis.XAxisPosition.TOP_INSIDE
                textSize = 10f
                textColor = Color.WHITE
                setDrawAxisLine(false)
                setDrawGridLines(true)
                textColor = Color.rgb(255, 192, 56)
                setCenterAxisLabels(true)
                granularity = 1f // one hour
                valueFormatter = object : ValueFormatter() {
                    private val mFormat: SimpleDateFormat =
                        SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH)

                    override fun getFormattedValue(value: Float, axis: AxisBase): String {
                        val millis: Long = TimeUnit.HOURS.toMillis(value.toLong())
                        return mFormat.format(Date(millis))
                    }
                }
            }

            axisLeft.apply {
                setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
                textColor = ColorTemplate.getHoloBlue()
                setDrawGridLines(true)
                isGranularityEnabled = true
                axisMinimum = 0f
                axisMaximum = 170f
                yOffset = -9f
                textColor = Color.rgb(255, 192, 56)


            }

            axisRight.isEnabled = false

        }



        return root
    }
}