package com.thepyprogrammer.androidLib.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData

class MutableErrorLiveData : MutableLiveData<String>("DON'T LOGIN") {
    override fun setValue(value: String) {
        super.setValue(value)
        Log.d("AUTH", value)
    }

    fun setValue(value: String, log: String) {
        super.setValue(value)
        Log.d("AUTH", log)
    }
}