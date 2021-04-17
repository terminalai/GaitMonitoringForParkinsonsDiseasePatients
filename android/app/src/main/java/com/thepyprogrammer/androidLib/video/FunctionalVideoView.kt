package com.thepyprogrammer.androidLib.video

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView


class FunctionalVideoView : VideoView {
    private lateinit var mListener: PlayPauseListener

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    fun setPlayPauseListener(listener: PlayPauseListener) {
        mListener = listener
    }

    override fun pause() {
        super.pause()
        mListener.onToggle(false)
    }

    override fun start() {
        super.start()
        mListener.onToggle(true)
    }

    fun toggle() = run {
        if (isPlaying) pause()
        else start()
        isPlaying
    }

    fun interface PlayPauseListener {
        fun onToggle(isPlaying: Boolean)
    }
}