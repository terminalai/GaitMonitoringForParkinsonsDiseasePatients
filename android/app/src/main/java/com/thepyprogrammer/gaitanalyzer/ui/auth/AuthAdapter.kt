package com.thepyprogrammer.gaitanalyzer.ui.auth

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thepyprogrammer.gaitanalyzer.ui.auth.login.LoginFragment
import com.thepyprogrammer.gaitanalyzer.ui.auth.register.RegisterFragment

class AuthAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) =
        when (position % itemCount) {
            0 -> LoginFragment()
            else -> RegisterFragment()
        }
}