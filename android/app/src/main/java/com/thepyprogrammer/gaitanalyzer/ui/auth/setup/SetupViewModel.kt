package com.thepyprogrammer.gaitanalyzer.ui.auth.setup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thepyprogrammer.androidlib.livedata.MutableErrorLiveData
import com.thepyprogrammer.androidlib.livedata.MutableStringLiveData
import com.thepyprogrammer.gaitanalyzer.model.account.Patient
import com.thepyprogrammer.gaitanalyzer.model.account.User
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.ktlib.crypto.AES

class SetupViewModel : ViewModel() {
    var pName = MutableStringLiveData("")
    var phoneNumber = MutableLiveData("")
    var password = MutableStringLiveData("")
    var userResult = MutableLiveData(User.empty())
    var error = MutableErrorLiveData()

    private val aes = AES()

    fun setup() {
        val name = pName.value
        val pw = password.value
        val phone = phoneNumber.value
        val type = FirebaseUtil.type

        error.setValue(
            "",
            "${FirebaseUtil.user?.name} ${FirebaseUtil.user?.type} ${FirebaseUtil.user?.password}"
        )

        var encryptedCode =
            aes.encrypt(
                "${FirebaseUtil.user?.name} ${FirebaseUtil.user?.type} ${FirebaseUtil.user?.password}",
                "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients"
            )
        if (encryptedCode == null) encryptedCode = "$name$type$pw"

        encryptedCode.replace(Regex("[/\\\\]+"), "")

        FirebaseUtil
            .userCollection()
            .document(encryptedCode)
            .update("emergencyContact", phone)
            .addOnSuccessListener {
                if (phone != null) {
                    (FirebaseUtil.user as Patient).phone = phone
                    userResult.value = FirebaseUtil.user
                } else error.setValue("Phone Number invalid")
            }.addOnFailureListener {
                error.setValue("Couldn't Update!")
            }
    }
}