package com.thepyprogrammer.gaitanalyzer.model.account.firebase

import android.app.Activity
import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.ktx.storage
import com.thepyprogrammer.gaitanalyzer.model.account.data.Caregiver
import com.thepyprogrammer.gaitanalyzer.model.account.data.Patient
import com.thepyprogrammer.gaitanalyzer.model.account.data.User
import java.io.File
import java.io.PrintWriter

object FirebaseUtil {
    private var FIRESTORE: FirebaseFirestore? = null
    private var STORAGE: FirebaseStorage? = null

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

    fun uploadImage(photoUri: Uri) {
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${user?.name}.jpg")

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


}