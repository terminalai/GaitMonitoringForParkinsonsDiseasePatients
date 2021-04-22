package com.thepyprogrammer.gaitanalyzer.model.firebase

import android.app.Activity
import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.ktx.storage
import com.thepyprogrammer.gaitanalyzer.model.account.Caregiver
import com.thepyprogrammer.gaitanalyzer.model.account.Patient
import com.thepyprogrammer.gaitanalyzer.model.account.User
import java.io.File
import java.io.PrintWriter

object FirebaseUtil {
    private var FIRESTORE: FirebaseFirestore? = null
    private var STORAGE: FirebaseStorage? = null

    var user: User? = null

    // Connect to the Cloud Firestore
    private val firestore: FirebaseFirestore
        get() {
            if (FIRESTORE == null) FIRESTORE = Firebase.firestore
            return FIRESTORE!!
        }

    private val storage: FirebaseStorage
        get() {
            if (STORAGE == null) STORAGE = Firebase.storage
            return STORAGE!!
        }



    fun uploadImage(photoUri: Uri) {
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${user?.id}.jpg")
        imageRef.putFile(photoUri)
    }
    fun uploadFreeze(file: File) {
        val storageRef = storage.reference
        val freezeRef = storageRef.child("freezes/${user?.getFileName()}.txt")
        val uri = Uri.fromFile(file)
        freezeRef.putFile(uri)
    }
    fun uploadFreeze(file: File, onFinish: () -> Unit) {
        val storageRef = storage.reference
        val freezeRef = storageRef.child("freezes/${user?.getFileName()}.txt")
        val uri = Uri.fromFile(file)
        freezeRef.putFile(uri).addOnCompleteListener {
            onFinish()
        }
    }

    fun retrieveFreeze(activity: Activity, onFinish: () -> Unit) {
        try {
            val storageRef = storage.reference
            val freezeRef = storageRef.child("freezes/${user?.getFileName()}.txt")

            val localFile = File(activity.filesDir, "freezes.txt")
            if (!localFile.exists()) localFile.createNewFile()
            freezeRef.getFile(localFile)
        } catch (e: StorageException) {
        }
        onFinish()
    }

    fun retrieveImage(activity: Activity, onFinish: () -> Unit) {
        try {
            val storageRef = storage.reference
            val imageRef = storageRef.child("images/${user?.id}.jpg")

            val localFile = File(activity.filesDir, "profile.jpg")
            if (!localFile.exists()) localFile.createNewFile()
            imageRef.getFile(localFile).addOnSuccessListener {
                val uri = Uri.fromFile(localFile)
                val imageInfoFile = File(activity.filesDir, "profileImageURI.txt")
                val output = PrintWriter(imageInfoFile)
                output.println(uri.toString())
                output.close()
            }.addOnFailureListener {
            }
        } catch (e: StorageException) {}
        onFinish()
    }

    fun userCollection() = firestore.collection("users")

    var type: String = ""

    fun newUser(name: String, password: String, encryptedCode: String) = run {
        if (type == "caregiver") Caregiver(name, password, encryptedCode)
        else Patient(name, password, encryptedCode)
    }

    fun imageRef() = run {
        val storageRef = storage.reference
        storageRef.child("images/${user?.id}.jpg")
    }


}