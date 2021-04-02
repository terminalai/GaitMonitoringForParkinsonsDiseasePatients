package com.thepyprogrammer.gaitanalyzer.ui.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.thepyprogrammer.greenpass.model.account.Result
import com.thepyprogrammer.greenpass.model.account.VaccinatedUser
import com.thepyprogrammer.greenpass.model.firebase.FirebaseUtil

class AuthViewModel(): ViewModel() {
    var pName = MutableLiveData("")
    var NRIC = MutableLiveData("")
    var date = MutableLiveData(Timestamp.now())
    var password = MutableLiveData("")
    var user_result = MutableLiveData(VaccinatedUser("", "", Timestamp(0, 0), "old"))

    fun register(){
        val fullName = pName.value!!.trim()
        val nric = NRIC.value!!.trim()
        val dateOfVaccine = date.value!!
        val pw = password.value!!
        val data = hashMapOf(
            "fullName" to fullName,
            "nric" to nric,
            "dateOfVaccine" to dateOfVaccine,
            "password" to pw
        )
        Log.d("TAG", "$fullName $nric $pw")

        FirebaseUtil.userCollection().document(nric).get()
            .addOnSuccessListener {
                val data_ = it?.data
                if (data_ != null){
                    val user = VaccinatedUser("", "", Timestamp.now(), "")
                    user_result.value = user
                    Result.Error(Exception("It seems you don't exist."))
                }
                else{

                    if (pw.length >= 8) {
                        FirebaseUtil.userCollection()
                                .document(nric)
                                .set(data)
                            .addOnSuccessListener {
                                user_result.value = VaccinatedUser(nric, fullName, dateOfVaccine, pw)
                                Log.d("TAG", "Data is Nice!")
                            }
                            .addOnFailureListener {
                                Log.d("TAG", "Data is Not Nice!")
                                val user = VaccinatedUser("", "", Timestamp.now(), "")
                                user_result.value = user
                            }

                    } else {
                        Result.Error(Exception("Password is too small!"))
                        val user = VaccinatedUser("", "", Timestamp.now(), "3")
                        user_result.value = user
                        Log.d("TAG", "Password Set to 3")
                    }

                }
            }.addOnFailureListener {}
    }

    fun login(){
        val nric = NRIC.value!!.trim()
        val password = this.password.value!!
        var data: Map<String?, Any?>?

        FirebaseUtil.userCollection().document(nric).get()
                .addOnSuccessListener {
                    data = it?.data
                    when {
                        data == null -> {
                            val user = VaccinatedUser("", "", Timestamp.now(), "")
                            user_result.value = user
                            Result.Error(Exception("It seems you don't exist."))
                        }
                        (data!!["password"] as String) == password -> {
                            val fullName = data!!["fullName"] as String
                            val user = VaccinatedUser(nric, fullName, data!!["dateOfVaccine"] as Timestamp, password)
                            user_result.value = user
                            Log.d("TAG", "Data is Correct!")
                        }
                        else -> {
                            val user = VaccinatedUser("", "", Timestamp.now(), "")
                            user_result.value = user
                            Log.d("TAG", "Data is Wrong!")
                            Result.Error(Exception("It seems you don't exist."))
                        }
                    }
                }.addOnFailureListener {
                }
    }
}