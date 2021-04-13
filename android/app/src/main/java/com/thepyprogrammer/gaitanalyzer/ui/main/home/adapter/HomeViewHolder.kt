package com.thepyprogrammer.gaitanalyzer.ui.main.home.adapter

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.ui.main.home.view.HomeCardLayout

class HomeViewHolder(val layout: HomeCardLayout) : RecyclerView.ViewHolder(layout) {
    fun setImage(drawable: Drawable?) {
        layout.avatar.setImageDrawable(drawable)
    }

    fun setDescription(desc: String) {
        layout.content.text = desc
    }

    fun set(desc: String, drawable: Drawable?) {
        setImage(drawable)
        setDescription(desc)
    }

}