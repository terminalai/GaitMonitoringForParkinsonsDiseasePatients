package com.thepyprogrammer.gaitanalyzer.model.firebase

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.ktx.storage
import com.thepyprogrammer.gaitanalyzer.model.account.Caregiver
import com.thepyprogrammer.gaitanalyzer.model.account.Patient
import com.thepyprogrammer.gaitanalyzer.model.account.User
import com.thepyprogrammer.ktlib.crypto.AES
import java.io.File
import java.io.PrintWriter

object FirebaseUtil {
    private var FIRESTORE: FirebaseFirestore? = null
    private var STORAGE: FirebaseStorage? = null
    private var FUNCTIONS: FirebaseFunctions? = null


    var user: User? = null

    // Connect to the Cloud Firestore
    val firestore: FirebaseFirestore
        get() {
            if (FIRESTORE == null) FIRESTORE = Firebase.firestore
            return FIRESTORE!!
        }

    val storage: FirebaseStorage
        get() {
            if (STORAGE == null) STORAGE = Firebase.storage
            return STORAGE!!
        }

    val functions: FirebaseFunctions
        get() {
            if (FUNCTIONS == null) FUNCTIONS = Firebase.functions
            return FUNCTIONS!!
        }


    fun uploadImage(photoUri: Uri) {
        val storageRef = storage.reference
        val aes = AES()

        var encryptedCode =
            aes.encrypt("${user?.name}${user?.type}${user?.password}", "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients")
        if (encryptedCode == null) encryptedCode = "${user?.name}${user?.type}${user?.password}"

        val imageRef = storageRef.child("images/${encryptedCode}.jpg")

        val uploadTask = imageRef.putFile(photoUri)
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            // nothing to be implemented
        }.addOnSuccessListener {
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
    fun uploadFreeze(file: File, context: Context) {
        val storageRef = storage.reference
        val aes = AES()

        var encryptedCode =
            aes.encrypt("${user?.name}${user?.type}${user?.password}", "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients")
        if (encryptedCode == null) encryptedCode = "${user?.name}${user?.type}${user?.password}"

        val freezeRef = storageRef.child("freezes/${encryptedCode}.txt")

        val uri = FileProvider.getUriForFile(context, "com.thepyprogrammer.fileprovider", file)
        val uploadTask = freezeRef.putFile(uri)
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            // nothing to be implemented
        }.addOnSuccessListener {
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    fun retrieveFreeze(activity: Activity) {
        try {
            val storageRef = storage.reference
            val aes = AES()

            var encryptedCode =
                aes.encrypt("${user?.name}${user?.type}${user?.password}", "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients")
            if (encryptedCode == null) encryptedCode = "${user?.name}${user?.type}${user?.password}"

            val freezeRef = storageRef.child("freezes/${encryptedCode}.txt")

            val localFile = File(activity.filesDir, "freezes.txt")
            if (!localFile.exists()) localFile.createNewFile()
            freezeRef.getFile(localFile)
        } catch (e: StorageException) {
        }
    }

    fun retrieveImage(activity: Activity) {
        try {
            val storageRef = storage.reference
            val imageRef = storageRef.child("images/${user?.name}.jpg")

            val localFile = File(activity.filesDir, "profile.jpg")
            if (!localFile.exists()) localFile.createNewFile()
            var success = false
            imageRef.getFile(localFile).addOnSuccessListener {
                success = true
            }.addOnFailureListener {
            }
            if (success) {
                val uri = Uri.fromFile(localFile)
                val imageInfoFile = File(activity.filesDir, "profileImageURI.txt")
                val output = PrintWriter(imageInfoFile)
                output.println(uri.toString())
                output.close()
            }
        } catch (e: StorageException) {
        }
    }

    fun userCollection() = firestore.collection("users")

    var type: String = ""

    fun newUser(name: String, password: String) = run {
        if (type == "caregiver") Caregiver(name, password)
        else Patient(name, password)
    }

    fun predict() {
        functions.getHttpsCallable("call")
    }


}