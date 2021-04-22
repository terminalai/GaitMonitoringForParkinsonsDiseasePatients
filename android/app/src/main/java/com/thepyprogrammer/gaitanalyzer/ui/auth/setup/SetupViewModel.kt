package com.thepyprogrammer.gaitanalyzer.ui.auth.setup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thepyprogrammer.androidlib.livedata.MutableErrorLiveData
import com.thepyprogrammer.androidlib.livedata.MutableStringLiveData
import com.thepyprogrammer.gaitanalyzer.model.account.Caregiver
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

        var data = hashMapOf<String, Any?>(
                "caregiverName" to name,
                "caregiverPassword" to pw,
                "phone" to phone
        )

        error.setValue("", "$name caregiver $pw")

        FirebaseUtil.user?.id?.let { filename ->
            FirebaseUtil
                    .userCollection()
                    .document(filename)
                    .update(data)
                    .addOnSuccessListener {
                        if (phone != null) {
                            Log.d("AUTH", "Data is Nice!")
                            (FirebaseUtil.user as Patient).phone = phone
                            (FirebaseUtil.user as Patient).caregiver = Caregiver(name, pw, encrypt("${name}caregiver${pw}"))
                            //userResult.value = FirebaseUtil.user
                        } else error.setValue("Phone Number invalid")
                    }.addOnFailureListener {
                        error.setValue("Couldn't Update!")
                    }
        }

        data = hashMapOf(
                "name" to name,
                "password" to pw,
                "patientName" to FirebaseUtil.user?.name,
                "patientPassword" to FirebaseUtil.user?.password
        )
        val docRef = FirebaseUtil
                .userCollection()
                .document(encrypt("${name}caregiver${pw}"))
        try {
            docRef.update(data)
                    .addOnSuccessListener {
                        Log.d("AUTH", "Caregiver exists!")
                        userResult.value = FirebaseUtil.user
                    }.addOnFailureListener {
                    Log.d("AUTH", "Caregiver does not exist!")
                        docRef.set(data)
                            .addOnSuccessListener {
                                userResult.value = FirebaseUtil.user
                            }.addOnFailureListener {
                                error.setValue("Couldn't Update!")
                            }
                    }
        } catch(e: Exception) {
            Log.d("AUTH", "Caregiver does not exist!")
            docRef.set(data)
                    .addOnSuccessListener {
                        Log.d("AUTH", "Caregiver created!")
                        userResult.value = FirebaseUtil.user
                    }.addOnFailureListener {
                        error.setValue("Couldn't Update!")
                    }
        }
    }

    private fun encrypt(string: String): String {
        var encryptedCode = aes.encrypt(
                string,
                "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients"
        )
        if (encryptedCode == null) encryptedCode = string
        encryptedCode.replace(Regex("/+"), "").replace(Regex("\\\\+"), "")
        return encryptedCode
    }
}