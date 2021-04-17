package com.thepyprogrammer.gaitanalyzer.ui.main.freeze

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData.FreezeDataFragment

class FreezeAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) =
        when (position % 3) {
            0 -> FreezeDataFragment()
            1 -> Fragment()
            else -> Fragment()
        }
}