package com.thepyprogrammer.gaitanalyzer.ui.singlecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.thepyprogrammer.gaitanalyzer.R

class SingleCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
        val completeViewModel = ViewModelProvider(this).get(CompleteViewModel::class.java)
    }
}