package com.thepyprogrammer.gaitanalyzer.ui.onboarding

import agency.tango.materialintroscreen.SlideFragment
import agency.tango.materialintroscreen.SlideFragmentBuilder
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentFinalSlideBinding
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity


class FinalSlideFragment: SlideFragment() {
    private lateinit var binding: FragmentFinalSlideBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinalSlideBinding.inflate(inflater, container, false)
        val view = binding.root
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.fragment_container,
                SlideFragmentBuilder()
                    .backgroundColor(R.color.fourth_slide_background)
                    .buttonsColor(R.color.fourth_slide_buttons)
                    .image(R.drawable.logo)
                    .title("So, let's start off!")
                    .description("Are you ready?")
                    .build()
            )
            ?.commitAllowingStateLoss()
        binding.move.setOnClickListener {
            val preferences = activity?.getSharedPreferences("preferences", MODE_PRIVATE)
            preferences?.edit()?.putBoolean("onboardingDone", true)?.apply()

            (activity as MainActivity).navController.navigate(R.id.nav_identification)
        }
        return view
    }
}