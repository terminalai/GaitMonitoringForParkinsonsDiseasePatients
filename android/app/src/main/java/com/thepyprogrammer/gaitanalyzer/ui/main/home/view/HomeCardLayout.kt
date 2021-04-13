package com.thepyprogrammer.gaitanalyzer.ui.main.home.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Color.DKGRAY
import android.graphics.Typeface.BOLD
import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import android.transition.TransitionManager
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.widget.ImageView
import android.widget.TextView
import com.squareup.contour.ContourLayout
import com.thepyprogrammer.gaitanalyzer.model.view.anim.PushOnPressAnimator

class HomeCardLayout(
    context: Context
) : ContourLayout(context) {

    val avatar = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        setBackgroundColor(Color.GRAY)
    }

    val title = TextView(context).apply {
        textSize = 16f
        text = "Title"
        setTextColor(Color.WHITE)
        setTypeface(typeface, BOLD)
    }

    val content = TextView(context).apply {
        textSize = 16f
        text = "..."
        setLineSpacing(0f, 1.33f)
    }

    init {
        background = PaintDrawable(DKGRAY)
        clipToOutline = true
        elevation = 20f.dip
        stateListAnimator = PushOnPressAnimator(this)
        registerBackPressListener()
        toggleCornerRadius(show = true)

        contourHeightOf { available ->
            if (isSelected) available else title.bottom() + 20.ydip
        }

        title.layoutBy(
            x = matchParentX(marginLeft = 20.dip, marginRight = 20.dip),
            y = topTo { avatar.bottom() + 20.ydip }
        )

        content.layoutBy(
            x = matchParentX(marginLeft = 20.dip, marginRight = 20.dip),
            y = topTo { title.bottom() + 20.ydip }
        )

        avatar.layoutBy(
            x = matchParentX(),
            y = topTo { parent.top() }.heightOf { 200.ydip }
        )

        setOnClickListener {
            TransitionManager.beginDelayedTransition(this)
            isSelected = !isSelected
            requestLayout()
        }
    }

    override fun getBackground() = super.getBackground() as PaintDrawable

    override fun setSelected(selected: Boolean) {
        if (isLaidOut && selected == this.isSelected) return
        super.setSelected(selected)
        toggleCornerRadius(show = !selected)
    }

    private fun toggleCornerRadius(show: Boolean) {
        // No idea why, but 0.0f causes the view to hide on animation end. Using 0.01 instead.
        val fromRadius = if (show) 0.01f else 12f.dip
        val toRadius = if (show) 12f.dip else 0.01f

        if (isLaidOut) {
            ObjectAnimator.ofFloat(fromRadius, toRadius)
                .apply { addUpdateListener { background.setCornerRadius(it.animatedValue as Float) } }
                .setDuration(200)
                .start()
        } else {
            background.setCornerRadius(toRadius)
        }
    }

    private fun registerBackPressListener() {
        isFocusableInTouchMode = true
        requestFocus()
        setOnKeyListener { _, keyCode, event ->
            if (isSelected && keyCode == KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                performClick()
            } else {
                false
            }
        }
    }
}