package com.thepyprogrammer.gaitanalyzer.model.view.anim

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.StateListAnimator
import android.view.View
import android.view.View.SCALE_X
import android.view.View.SCALE_Y
import android.view.animation.AccelerateDecelerateInterpolator

/** Plays a subtle push animation when [view] is pressed. */
class PushOnPressAnimator(private val view: View) : StateListAnimator() {
    init {
        addState(
                intArrayOf(android.R.attr.state_pressed),
                createAnimator(toScale = 0.95f)
        )
        addState(
                intArrayOf(-android.R.attr.state_pressed),
                createAnimator(toScale = 1f)
        )
    }

    private fun createAnimator(toScale: Float): Animator {
        val scaleX = PropertyValuesHolder.ofFloat(SCALE_X, toScale)
        val scaleY = PropertyValuesHolder.ofFloat(SCALE_Y, toScale)
        return ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
            duration = 80
            interpolator = AccelerateDecelerateInterpolator()
        }
    }
}