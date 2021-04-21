package com.thepyprogrammer.gaitanalyzer.ui.onboarding

import agency.tango.materialintroscreen.SlideFragmentBuilder
import androidx.fragment.app.FragmentActivity
import com.thepyprogrammer.androidlib.viewpager.ViewPagerAdapter
import com.thepyprogrammer.gaitanalyzer.R

class OnboardingAdapter(fa: FragmentActivity) :
    ViewPagerAdapter(
        fa,
        mutableListOf(
            SlideFragmentBuilder()
                .backgroundColor(R.color.primary)
                .buttonsColor(R.color.primary_dark)
                .image(R.drawable.logo)
                .title("Welcome to Gait Analyzer")
                .description("For Parkinson's Disease Patients")
                .build(),

            SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.black)
                .image(R.drawable.fog)
                .title("Made For Parkinson's Disease Patients")
                .description("We'll make sure you are safe, and sound.")
                .build(),

            SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.black)
                .image(R.drawable.parkinson)
                .title("Detecting FoG")
                .description("Since 2021")
                .build(),

            SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.black)
                .image(R.drawable.fall)
                .title("Preventing Falls Too")
                .description("Since 2021")
                .build(),

            FinalSlideFragment()
        )
    )