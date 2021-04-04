package com.thepyprogrammer.gaitanalyzer.ui.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thepyprogrammer.gaitanalyzer.R
import java.util.*


class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        // Initialize ViewPager view
        val viewPager = findViewById<ViewPager>(R.id.viewPagerOnBoarding)
        // create ViewPager adapter
        val viewPagerAdapter = ViewPagerAdapter(
                supportFragmentManager
        )

        // Add All Fragments to ViewPager
        viewPagerAdapter.addFragment(StepOneFragment())
        viewPagerAdapter.addFragment(StepTwoFragment())
        viewPagerAdapter.addFragment(StepThreeFragment())
        viewPagerAdapter.addFragment(StepFourFragment())

        // Set Adapter for ViewPager
        viewPager.adapter = viewPagerAdapter

        // Setup dot's indicator
        val tabLayout = findViewById<TabLayout>(R.id.tabLayoutIndicator)
        tabLayout.setupWithViewPager(viewPager)
    }

    // ViewPager Adapter class
    internal inner class ViewPagerAdapter(supportFragmentManager: FragmentManager?) :
            FragmentPagerAdapter(supportFragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mList: MutableList<Fragment> = ArrayList()
        override fun getItem(i: Int): Fragment {
            return mList[i]
        }

        override fun getCount(): Int {
            return mList.size
        }

        fun addFragment(fragment: Fragment) {
            mList.add(fragment)
        }
    }
}
