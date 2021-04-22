package com.thepyprogrammer.gaitanalyzer.ui.auth

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

class AuthViewModel : ViewModel() {
    var pName = MutableStringLiveData("")
    var password = MutableStringLiveData("")
    var userResult = MutableLiveData(User.empty())
    var error = MutableErrorLiveData()
    var isLogin = MutableLiveData(false)

    private val aes = AES()

    fun register() {
        val name = pName.value
        val pw = password.value
        val type = FirebaseUtil.type
        val otherType = if(type == "patient") "caregiver" else "patient"

        val data = hashMapOf(
            "name" to name,
            "password" to pw,
            "type" to type,
            "phone" to "",
            "${otherType}Name" to "",
            "${otherType}Password" to ""
        )

        error.setValue("", "$name $type $pw")

        val encryptedCode = encrypt("$name$type$pw")

        FirebaseUtil.userCollection().document(encryptedCode).get()
            .addOnSuccessListener {
                val dataset = it?.data
                if (dataset != null) {
                    isLogin.value = true
                    Log.d("AUTH", "Logging In Instead.")
                    if (pw == (dataset["password"] as String)) {
                        userResult.value = FirebaseUtil.newUser(dataset["name"] as String, pw, encryptedCode)
                        if(userResult.value is Patient) {
                            (userResult.value as Patient).phone = dataset["phone"] as String
                            val caregiverName = dataset["${otherType}Name"] as String
                            val caregiverPw = dataset["${otherType}Password"] as String
                            (userResult.value as Patient).caregiver = Caregiver(caregiverName, caregiverPw, encrypt("${caregiverName}caregiver${caregiverPw}"))
                        } else {
                            val patientName = dataset["${otherType}Name"] as String
                            val patientPw = dataset["${otherType}Password"] as String
                            (userResult.value as Caregiver).patient = Patient(patientName, patientPw, encrypt("${patientName}patient${patientPw}"))

                        }
                        Log.d("AUTH", "Data is Correct!")
                    }
                    else error.setValue("It seems you don't exist.")

                } else {
                    isLogin.value = false
                    if (pw.length >= 6) {
                        FirebaseUtil.userCollection()
                            .document(encryptedCode)
                            .set(data)
                            .addOnSuccessListener {
                                Log.d("AUTH", "Data is Nice!")
                                userResult.value = FirebaseUtil.newUser(name, pw, encryptedCode)
                            }
                            .addOnFailureListener {
                                error.setValue("Can't Process Account.")
                            }

                    } else {
                        error.setValue("Password is too small!")
                    }

                }
            }.addOnFailureListener {
                error.setValue("Couldn't Find You.")
            }
    }

    fun login() {
        val name = pName.value
        val pw = password.value
        val type = FirebaseUtil.type
        var data: Map<String?, Any?>?

        error.setValue("", "$name $type $pw")

        val encryptedCode = encrypt("$name$type$pw")

        FirebaseUtil.userCollection().document(encryptedCode).get()
            .addOnSuccessListener {
                data = it?.data
                when {
                    data == null -> {
                        error.setValue("It seems you don't exist.")
                    }
                    (data!!["password"] as String) == pw -> {
                        userResult.value = FirebaseUtil.newUser(data!!["name"] as String, pw, encryptedCode)
                        if(userResult.value is Patient) {
                            (userResult.value as Patient).phone = data!!["phone"] as String
                            val caregiverName = data!!["caregiverName"] as String
                            val caregiverPw = data!!["caregiverPassword"] as String
                            (userResult.value as Patient).caregiver = Caregiver(caregiverName, caregiverPw, encrypt("${caregiverName}caregiver${caregiverPw}"))
                        }
                        Log.d("AUTH", "Data is Correct!")
                    }
                    else -> error.setValue("It seems you don't exist.")
                }
            }.addOnFailureListener {
                error.setValue("Couldn't Find You.")
            }
    }

    private fun encrypt(string: String): String {
        var encryptedCode = aes.encrypt(
                string,
                "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients"
        )
        if (encryptedCode == null) encryptedCode = string
        encryptedCode.replace(Regex("[/\\\\]+"), "")
        return encryptedCode
    }
}