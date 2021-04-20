package com.thepyprogrammer.androidlib.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

open class ViewPagerAdapter(
    fa: FragmentActivity,
    private val fragmentList: List<Fragment> = mutableListOf()
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position % itemCount]
}