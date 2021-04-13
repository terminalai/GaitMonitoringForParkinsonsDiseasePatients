package com.thepyprogrammer.gaitanalyzer.ui.main.home.adapter

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.ui.main.home.view.HomeCardLayout

class HomeViewHolder(val layout: HomeCardLayout) : RecyclerView.ViewHolder(layout) {
    fun setImage(drawable: Drawable?) {
        layout.avatar.setImageDrawable(drawable)
    }

    fun setTitle(title: String) {
        layout.title.text = title
    }

    fun setDescription(desc: String) {
        layout.content.text = desc
    }

    fun set(title: String, desc: String, drawable: Drawable?) {
        setImage(drawable)
        setTitle(title)
        setDescription(desc)
    }

}