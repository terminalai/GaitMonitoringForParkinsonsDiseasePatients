package com.thepyprogrammer.gaitanalyzer.ui.main.home

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import com.thepyprogrammer.gaitanalyzer.R

class FallAlert(activity: Activity): Dialog(activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.alert_fall)

    }
}