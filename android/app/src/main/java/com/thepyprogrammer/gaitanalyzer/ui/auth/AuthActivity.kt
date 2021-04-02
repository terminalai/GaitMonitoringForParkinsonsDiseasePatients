package com.thepyprogrammer.gaitanalyzer.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.thepyprogrammer.greenpass.R
import com.thepyprogrammer.greenpass.ui.main.MainViewModel

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        viewPager.adapter = AuthAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position % 2) {
                0 -> tab.text = "Login"
                else -> tab.text = "Register"
            }
        }.attach()
    }
}
