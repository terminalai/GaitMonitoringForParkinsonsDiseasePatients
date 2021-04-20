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
                .image(R.drawable.ic_coding)
                .title("Code Optimized")
                .description("Experience the Greatness of THEPYPROGRAMMER")
                .build(),

            SlideFragmentBuilder()
                .backgroundColor(R.color.fourth_slide_background)
                .buttonsColor(R.color.fourth_slide_buttons)
                .image(R.drawable.ic_web_page)
                .title("Many Functions")
                .description("Customized for you!")
                .build(),

            SlideFragmentBuilder()
                .backgroundColor(R.color.fourth_slide_background)
                .buttonsColor(R.color.fourth_slide_buttons)
                .image(R.drawable.ic_phone)
                .title("Detects Freezes and Falls")
                .description("Fret Not About Your Safety!")
                .build(),
            FinalSlideFragment()
        )
    )