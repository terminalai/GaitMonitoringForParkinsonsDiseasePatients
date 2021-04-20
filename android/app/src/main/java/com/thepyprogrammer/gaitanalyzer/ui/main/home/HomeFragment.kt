package com.thepyprogrammer.gaitanalyzer.ui.main.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentHomeBinding
import com.thepyprogrammer.gaitanalyzer.model.account.data.Caregiver
import com.thepyprogrammer.gaitanalyzer.model.account.data.Patient
import com.thepyprogrammer.gaitanalyzer.model.account.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.gaitanalyzer.ui.main.MainViewModel


class HomeFragment : Fragment(), OnChartValueSelectedListener {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mainViewModel: MainViewModel




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root

        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        mainViewModel =
            ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        if(homeViewModel.isWalkMode.value == true) {
            binding.toggleWalk.text = getString(R.string.end_walk)
            binding.chart.apply {
                visibility = View.VISIBLE

                description.isEnabled = true
                setTouchEnabled(true)
                isDragEnabled = true
                setScaleEnabled(true)
                setDrawGridBackground(false)
                setPinchZoom(true)
                setBackgroundColor(Color.LTGRAY)
                data = LineData()
                data.setValueTextColor(Color.WHITE)

                legend.apply {
                    form = Legend.LegendForm.LINE
                    textColor = Color.WHITE
                }

                axisLeft.apply {
                    textColor = Color.WHITE
                    axisMaximum = 100f
                    axisMinimum = -100f
                    setDrawGridLines(true)
                }

                axisRight.isEnabled = false

            }

            feedMultiple()
        } else binding.chart.visibility = View.INVISIBLE

        if(FirebaseUtil.user is Caregiver) binding.patientContainer.visibility = View.GONE

        binding.toggleWalk.setOnClickListener {
            if(homeViewModel.isWalkMode.value == false) {
                homeViewModel.task.value = WalkingMode(activity as MainActivity) {
                    if(activity != null) FallAlert(activity as Activity).show()
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:${(FirebaseUtil.user as Patient).phone}")
                    context?.startActivity(intent)
                }
                binding.toggleWalk.text = getString(R.string.end_walk)
                homeViewModel.task.value?.execute()

                binding.chart.apply {
                    visibility = View.VISIBLE

                    description.isEnabled = true
                    setTouchEnabled(true)
                    isDragEnabled = true
                    setScaleEnabled(true)
                    setDrawGridBackground(false)
                    setPinchZoom(true)
                    setBackgroundColor(Color.LTGRAY)
                    data = LineData()
                    data.setValueTextColor(Color.WHITE)

                    legend.apply {
                        form = Legend.LegendForm.LINE
                        textColor = Color.WHITE
                    }

                    axisLeft.apply {
                        textColor = Color.WHITE
                        axisMaximum = 100f
                        axisMinimum = -100f
                        setDrawGridLines(true)
                    }

                    axisRight.isEnabled = false

                }

                feedMultiple()
                homeViewModel.isWalkMode.value = true
            } else {
                binding.toggleWalk.text = getString(R.string.start_walk)
                homeViewModel.task.value?.cancel(true)
                binding.chart.visibility = View.INVISIBLE
                thread?.interrupt()
                homeViewModel.isWalkMode.value = false
            }





        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
    }


    private fun addEntry() {
        val data: LineData = binding.chart.data
        var set1 = data.getDataSetByIndex(0)
        // set.addEntry(...); // can be called as well
        if (set1 == null) {
            set1 = createSet("AccX")
            data.addDataSet(set1)
        }

        var set2 = data.getDataSetByIndex(0)
        // set.addEntry(...); // can be called as well
        if (set2 == null) {
            set2 = createSet("AccY")
            data.addDataSet(set1)
        }

        var set3 = data.getDataSetByIndex(0)
        // set.addEntry(...); // can be called as well
        if (set3 == null) {
            set3 = createSet("AccZ")
            data.addDataSet(set1)
        }


        data.addEntry(homeViewModel.task.value?.acc?.x?.toFloat()?.let {
            Entry(set1.entryCount.toFloat(),
                it, 0)
        }, 0)
        data.addEntry(homeViewModel.task.value?.acc?.y?.toFloat()?.let {
            Entry(set2.entryCount.toFloat(),
                it, 0)
        }, 1)
        data.addEntry(homeViewModel.task.value?.acc?.z?.toFloat()?.let {
            Entry(set3.entryCount.toFloat(),
                it, 0)
        }, 2)
        data.notifyDataChanged()

        // let the chart know it's data has changed
        binding.chart.notifyDataSetChanged()

        // limit the number of visible entries
        binding.chart.setVisibleXRangeMaximum(120f)
        // chart.setVisibleYRange(30, AxisDependency.LEFT);

        // move to the latest entry
        binding.chart.moveViewToX(data.entryCount.toFloat())

        // this automatically refreshes the chart (calls invalidate())
        // chart.moveViewTo(data.getXValCount()-7, 55f,
        // AxisDependency.LEFT);
    }

    private fun createSet(name: String): LineDataSet {
        val set = LineDataSet(null, name)
        set.axisDependency = AxisDependency.LEFT
        set.color = ColorTemplate.getHoloBlue()
        set.setCircleColor(Color.WHITE)
        set.lineWidth = 2f
        set.circleRadius = 4f
        set.fillAlpha = 65
        set.fillColor = ColorTemplate.getHoloBlue()
        set.highLightColor = Color.rgb(244, 117, 117)
        set.valueTextColor = Color.WHITE
        set.valueTextSize = 9f
        set.setDrawValues(false)
        return set
    }

    private var thread: Thread? = null

    private fun feedMultiple() {
        thread?.interrupt()
        thread = Thread {
            for (i in 0..999) {

                // Don't generate garbage runnables inside the loop.
                requireActivity().runOnUiThread(this::addEntry)
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread!!.start()
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Log.i("Entry selected", e.toString())
    }

    override fun onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.")
    }

    override fun onDestroy() {
        super.onDestroy()
        thread?.interrupt()
    }

    override fun onDetach() {
        super.onDetach()
        thread?.interrupt()
    }
}