package com.thepyprogrammer.gaitanalyzer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var image = MutableLiveData<String>()

    var pName = MutableLiveData("name")

    fun getResultName() = pName

}