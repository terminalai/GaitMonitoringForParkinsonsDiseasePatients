package com.thepyprogrammer.gaitanalyzer.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.ui.main.home.adapter.HomeAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val homeAdapter = HomeAdapter(this,
            mutableListOf()
        )

        val homeFeed = root.findViewById<RecyclerView>(R.id.home_feed)
        homeFeed.adapter = homeAdapter
        homeFeed.layoutManager = LinearLayoutManager(activity)

        return root
    }
}