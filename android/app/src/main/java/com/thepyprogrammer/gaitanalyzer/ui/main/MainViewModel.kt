package com.thepyprogrammer.gaitanalyzer.ui.main

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thepyprogrammer.gaitanalyzer.model.Util
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.auth.AuthActivity
import java.io.File
import java.io.PrintWriter
import java.util.*

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var image = MutableLiveData<String>()

    var pName = MutableLiveData("name")
    var NRIC = MutableLiveData("IC")
    var date = MutableLiveData(Date())

    fun getResultName() = pName

    fun getResultNRIC() = NRIC


    fun setNRIC(NRIC: String): Boolean {
        if (Util.checkNRIC(NRIC)) {
            this.NRIC.value = NRIC
        }
        return NRIC.matches(Util.nricRegex)
    }

    fun logout(activity: Activity) {
        val accountDetails = File(activity.filesDir, "accountDetails.txt")
        if(accountDetails.exists())
            PrintWriter(accountDetails).close()

        FirebaseUtil.user = null
    }


}