package com.thepyprogrammer.gaitanalyzer.ui.main.freeze

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData.FreezeDataFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.freeze.indices.FIFragment

class FreezeAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) =
        when (position % itemCount) {
            0 -> FreezeDataFragment()
            1 -> FIFragment()
            else -> Fragment()
        }
}