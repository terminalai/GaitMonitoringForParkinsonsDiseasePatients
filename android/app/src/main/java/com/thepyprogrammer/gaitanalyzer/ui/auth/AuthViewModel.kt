package com.thepyprogrammer.gaitanalyzer.ui.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.crypto.AES
import com.thepyprogrammer.gaitanalyzer.model.account.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.model.livedata.MutableErrorLiveData
import com.thepyprogrammer.gaitanalyzer.model.livedata.MutableStringLiveData

class AuthViewModel : ViewModel() {
    var pName = MutableStringLiveData("")
    var password = MutableStringLiveData("")
    var userResult = MutableLiveData(User.empty())
    var error = MutableErrorLiveData()

    val aes = AES()

    fun register() {
        val name = pName.value
        val pw = password.value
        val type = FirebaseUtil.type

        val data = hashMapOf(
                "name" to name,
                "password" to pw,
                "type" to type
        )

        error.setValue("", "$name $type $pw")

        var encryptedCode = aes.encrypt("$name$type$pw", "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients")
        if(encryptedCode == null) encryptedCode = "$name$type$pw"

        encryptedCode.replace(Regex("[/\\\\]"), "")


        FirebaseUtil.userCollection().document(encryptedCode).get()
                .addOnSuccessListener {
                    val dataset = it?.data
                    if (dataset != null) {
                        Log.d("AUTH","Logging In Instead.")
                        login()
                    } else {
                        if (pw.length >= 6) {
                            FirebaseUtil.userCollection()
                                    .document(encryptedCode)
                                    .set(data)
                                    .addOnSuccessListener {
                                        Log.d("AUTH", "Data is Nice!")
                                        userResult.value = FirebaseUtil.newUser(name, pw)
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
        val password = this.password.value
        var data: Map<String?, Any?>?

        error.setValue("", "$name ${FirebaseUtil.type} $password")

        var encryptedCode = aes.encrypt("$name${FirebaseUtil.type}$password", "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients")
        if(encryptedCode == null) encryptedCode = "$name${FirebaseUtil.type}$password"

        encryptedCode.replace("/\\\\", "")

        FirebaseUtil.userCollection().document(encryptedCode).get()
                .addOnSuccessListener {
                    data = it?.data
                    when {
                        data == null -> {
                            error.setValue("It seems you don't exist.")
                        }
                        (data!!["password"] as String) == password -> {
                            userResult.value = FirebaseUtil.newUser(data!!["name"] as String, password)
                            Log.d("AUTH", "Data is Correct!")
                        }
                        else -> {
                            error.setValue("It seems you don't exist.")
                        }
                    }
                }.addOnFailureListener {
                    error.setValue("Couldn't Find You.")
                }
    }
}