package com.thepyprogrammer.gaitanalyzer.model.livedata

import androidx.lifecycle.MutableLiveData

class MutableStringLiveData(init: String) : MutableLiveData<String>(init) {
    override fun getValue(): String {
        return super.getValue()!!
    }
}