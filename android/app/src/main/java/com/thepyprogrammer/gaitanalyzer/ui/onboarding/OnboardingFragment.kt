package com.thepyprogrammer.gaitanalyzer.ui.onboarding


import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewPager.adapter = OnboardingAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

        binding.back.visibility = View.INVISIBLE
        binding.back.setOnClickListener {
            back()
        }
        binding.next.setOnClickListener {
            next()
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if(position == 0) {
                    binding.back.visibility = View.INVISIBLE
                } else binding.back.visibility = View.VISIBLE

                if(position == binding.viewPager.adapter?.itemCount?.minus(1)) {
                    binding.next.visibility = View.INVISIBLE
                } else binding.next.visibility = View.VISIBLE


            }
        })



        return view
    }

    fun back() {
        binding.viewPager.currentItem = binding.viewPager.currentItem - 1
    }

    fun next() {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    fun onBackPressed(): Boolean {
        if (binding.viewPager.currentItem != 0) {
            back()
        }
        return true
    }



}