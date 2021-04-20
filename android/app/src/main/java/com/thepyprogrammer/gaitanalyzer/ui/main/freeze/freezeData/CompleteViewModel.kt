package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class CompleteViewModel : ViewModel() {
    val dateSelected = MutableLiveData(LocalDate.now())
    val dateData = MutableLiveData(mutableListOf<LocalDateTime>())
}