package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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
import java.time.LocalDateTime

class FreezeDataFragment : Fragment() {
    private lateinit var binding: FragmentFreezeDataBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var adapter: RecyclerView.Adapter<*>? = null
    lateinit var homeViewModel: HomeViewModel
    private val dateData = mutableListOf<LocalDateTime>()
    private lateinit var completeViewModel: CompleteViewModel


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
                    val date = timeInMillis.toLocalDateTime()
                    dateData.add(date)
                }
            }
        }

        dateData.sort()

        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        completeViewModel =
            ViewModelProvider(requireActivity()).get(CompleteViewModel::class.java)

        completeViewModel.dateData.value = dateData

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val freezes: MutableList<Pair<String, Int>> = mutableListOf()
        val map = hashMapOf<String, Int>()
        dateData.forEach {
            val dateString = Util.dTF.format(it.toLocalDate())
            map[dateString] =
                if (map.containsKey(dateString) && map[dateString] != null)
                    map.getOrDefault(dateString, 0) + 1
                else 1
        }
        map.toSortedMap()

        map.forEach { (date, freeze) ->
            freezes.add(Pair(date, freeze))
        }

        freezes.sortBy { it.first }


        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.layoutManager = layoutManager
        adapter = FreezeDataAdapter((activity as AppCompatActivity), freezes)
        binding.recyclerView.adapter = adapter
        if(freezes.size == 0) {
            binding.recyclerContainer.visibility = View.GONE
            binding.imageContainer.visibility = View.VISIBLE
        } else {
            binding.recyclerContainer.visibility = View.VISIBLE
            binding.imageContainer.visibility = View.GONE
        }

    }
}