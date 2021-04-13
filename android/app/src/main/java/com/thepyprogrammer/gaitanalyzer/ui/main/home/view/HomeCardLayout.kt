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

    private val avatar = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    private val bio = TextView(context).apply {
        textSize = 16f
        text = "..."
    }

    init {
        avatar.layoutBy(
                x = leftTo { parent.left() }.widthOf { 60.xdip },
                y = topTo { parent.top() }.heightOf { 60.ydip }
        )
        bio.layoutBy(
                x = leftTo { avatar.right() + 16.xdip }.rightTo { parent.right() },
                y = topTo {
                    if (isSelected) parent.top() + 16.ydip
                    else avatar.centerY() - bio.height() / 2
                }.heightOf {
                    if (isSelected) bio.preferredHeight()
                    else 48.ydip
                }
        )
        contourHeightOf { bio.bottom() }

        setOnClickListener {
            TransitionManager.beginDelayedTransition(this)
            isSelected = !isSelected
            requestLayout()
        }
    }




}