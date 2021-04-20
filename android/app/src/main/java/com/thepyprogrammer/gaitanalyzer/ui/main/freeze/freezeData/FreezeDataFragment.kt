package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentFreezeDataBinding
import com.thepyprogrammer.gaitanalyzer.ui.main.home.HomeViewModel
import com.thepyprogrammer.ktlib.Util
import com.thepyprogrammer.ktlib.toLocalDateTime
import java.io.File
import java.io.FileReader
import java.time.LocalDate

class FreezeDataFragment : Fragment() {
    private lateinit var binding: FragmentFreezeDataBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var adapter: RecyclerView.Adapter<*>? = null
    lateinit var homeViewModel: HomeViewModel
    private val dateData = mutableListOf<LocalDate>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFreezeDataBinding.inflate(inflater, container, false)

        val freezesFile = File(activity?.filesDir, "freezes.txt")
        if (!freezesFile.exists()) freezesFile.createNewFile()

        with(FileReader(freezesFile)) {
            forEachLine {
                if (it.isNotEmpty()) {
                    val timeInMillis = it.trim().toLong()
                    val date = timeInMillis.toLocalDateTime().toLocalDate()
                    dateData.add(date)
                }
            }
        }

        dateData.sort()

        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val freezes: MutableList<Pair<String, Int>> = mutableListOf()
        val map = hashMapOf<String, Int>()
        dateData.forEach {
            val dateString = Util.dTF.format(it)
            map[dateString] =
                if (map.containsKey(dateString) && map[dateString] != null)
                    map.getOrDefault(dateString, 0) + 1
                else 1
        }
        map.toSortedMap()

        map.forEach { (date, freeze) ->
            freezes.add(Pair(date, freeze))
        }


        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.layoutManager = layoutManager
        adapter = FreezeDataAdapter((activity as AppCompatActivity), freezes)
        binding.recyclerView.adapter = adapter
    }
}