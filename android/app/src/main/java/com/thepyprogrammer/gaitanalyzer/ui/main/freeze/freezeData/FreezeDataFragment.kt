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

class FreezeDataFragment : Fragment() {
    private lateinit var binding: FragmentFreezeDataBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var adapter: RecyclerView.Adapter<*>? = null
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFreezeDataBinding.inflate(inflater, container, false)
        homeViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.layoutManager = layoutManager
        adapter = FreezeDataAdapter((activity as AppCompatActivity), homeViewModel)
        binding.recyclerView.adapter = adapter
    }
}