package com.thepyprogrammer.gaitanalyzer.ui.main.home.view

import android.content.Context
import android.transition.TransitionManager
import android.widget.ImageView
import android.widget.TextView
import com.squareup.contour.ContourLayout
import com.thepyprogrammer.gaitanalyzer.model.view.anim.PushOnPressAnimator

class HomeCardLayout(
    context: Context
) : ContourLayout(context) {

    val avatar = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    val content = TextView(context).apply {
        textSize = 16f
        text = "..."
    }

    init {
        stateListAnimator = PushOnPressAnimator(this)
        avatar.layoutBy(
                x = leftTo { parent.left() }.widthOf { 60.xdip },
                y = topTo { parent.top() }.heightOf { 60.ydip }
        )
        content.layoutBy(
                x = leftTo { avatar.right() + 16.xdip }.rightTo { parent.right() },
                y = topTo {
                    if (isSelected) parent.top() + 16.ydip
                    else avatar.centerY() - content.height() / 2
                }.heightOf {
                    if (isSelected) content.preferredHeight()
                    else 48.ydip
                }
        )
        contourHeightOf { content.bottom() }

        setOnClickListener {
            TransitionManager.beginDelayedTransition(this)
            isSelected = !isSelected
            requestLayout()
        }
    }




}