package com.thepyprogrammer.gaitanalyzer.ui.auth

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AuthAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) =
        when (position % 2) {
            0 -> LoginFragment()
            else -> RegisterFragment()
        }
}