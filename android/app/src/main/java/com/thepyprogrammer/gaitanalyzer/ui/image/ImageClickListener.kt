package com.thepyprogrammer.gaitanalyzer.ui.image

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment

class ImageClickListener(val context: Context?) : View.OnClickListener {
    constructor(fragment: Fragment) : this(fragment.context)

    override fun onClick(v: View?) {
        Intent(context, ImageDetailsActivity::class.java).also {
            context?.startActivity(it)
        }
    }
}