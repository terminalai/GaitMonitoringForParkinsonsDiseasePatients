package com.thepyprogrammer.gaitanalyzer.ui.main.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.model.data.HomeCardInfo
import com.thepyprogrammer.gaitanalyzer.ui.main.home.HomeFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.home.view.HomeCardLayout

class HomeAdapter(
        private val parentFragment: HomeFragment,
        private val items: MutableList<HomeCardInfo>
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        HomeCardLayout(parentFragment.requireContext())
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items[position]


    }

    override fun getItemCount() = 2

}