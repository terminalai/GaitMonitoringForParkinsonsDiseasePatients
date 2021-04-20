package com.thepyprogrammer.gaitanalyzer.ui.main.freeze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.google.android.material.tabs.TabLayoutMediator
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentFreezeBinding

class FreezeFragment : Fragment() {

    private lateinit var binding: FragmentFreezeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFreezeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewPager.adapter = FreezeAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position % 2) {
                0 -> "Freeze"
                else -> "Freezes"
            }
        }.attach()


        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }
}
