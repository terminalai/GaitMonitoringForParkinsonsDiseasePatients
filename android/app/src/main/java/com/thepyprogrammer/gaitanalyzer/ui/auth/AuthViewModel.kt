package com.thepyprogrammer.gaitanalyzer.ui.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thepyprogrammer.gaitanalyzer.model.account.Result
import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil

class AuthViewModel : ViewModel() {
    var pName = MutableLiveData("")
    var password = MutableLiveData("")
    var userResult = MutableLiveData(User.empty())

    fun register() {
        val name = pName.value!!.trim()
        val pw = password.value!!
        val type = userResult.value!!.type
        hashMapOf(
            "name" to name,
            "password" to pw,
            "type" to type
        )
        Log.d("TAG", "$name $type $pw")

        FirebaseUtil.userCollection().document(name).get()
            .addOnSuccessListener {
                val dataset = it?.data
                if (dataset != null) {
                    val user = User.empty()
                    userResult.value = user
                    Result.Error(Exception("It seems you don't exist."))
                } else {
                    if (pw.length >= 8) {
                        it?.data?.let { it1 ->
                            FirebaseUtil.userCollection()
                                .document(name)
                                .set(it1)
                                .addOnSuccessListener {
                                    userResult.value = User(name, pw, type)
                                    Log.d("TAG", "Data is Nice!")
                                }
                                .addOnFailureListener {
                                    Log.d("TAG", "Data is Not Nice!")
                                    val user = User.empty()
                                    userResult.value = user
                                }
                        }

                    } else {
                        Result.Error(Exception("Password is too small!"))
                        val user = User.empty()
                        userResult.value = user
                        Log.d("TAG", "Password Set to 3")
                    }

                }
            }.addOnFailureListener {}
    }

    fun login() {
        val name = pName.value!!.trim()
        val password = this.password.value!!
        var data: Map<String?, Any?>?

        FirebaseUtil.userCollection().document(name).get()
            .addOnSuccessListener {
                data = it?.data
                when {
                    data == null -> {
                        val user = User.empty()
                        userResult.value = user
                        Result.Error(Exception("It seems you don't exist."))
                    }
                    (data!!["password"] as String) == password -> {
                        val user = User(data!!["name"] as String, password, "caregiver")
                        userResult.value = user
                        Log.d("TAG", "Data is Correct!")
                    }
                    else -> {
                        val user = User.empty()
                        userResult.value = user
                        Log.d("TAG", "Data is Wrong!")
                        Result.Error(Exception("It seems you don't exist."))
                    }
                }
            }.addOnFailureListener {
            }
    }
}