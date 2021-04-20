package com.thepyprogrammer.gaitanalyzer.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thepyprogrammer.ktlib.array.Vector

class HomeViewModel : ViewModel() {
    val isWalkMode = MutableLiveData(false)

    val name = MutableLiveData("User")

    val accs = MutableLiveData(hashMapOf<Long, Vector>())
    val gyros = MutableLiveData(hashMapOf<Long, Vector>())
    val freezes = MutableLiveData(mutableListOf<Long>())
    val task = MutableLiveData<WalkingMode>(null)


    override fun onCleared() {
        super.onCleared()

    }
}