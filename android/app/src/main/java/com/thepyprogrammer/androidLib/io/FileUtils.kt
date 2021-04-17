package com.thepyprogrammer.androidLib.io

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


// Matches the file provider set in the manifest
const val FILE_PROVIDER_AUTHORITY = "com.example.androidme.fileprovider"

/**
 * Creates a temporary file
 */
fun createTempImageFile(application: Application): File? {
    var photoFile: File? = null

    try {
        val imageFileName = "JPEG_${createTimestamp()}"
        val storageDir = application.applicationContext.externalCacheDir

        photoFile = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )

    } catch (exception: IOException) {
        // Error occurred while creating the KFile
        Log.e("BitmapUtils", "Could not create temp file", exception)
    }
    return photoFile
}

/**
 * Creates a timestamp for our file
 */
fun createTimestamp(): String {
    return SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
}

/**
 * Gets a Uri for a your file
 */
fun createImageFileUri(applicationContext: Context, file: File): Uri {
    return FileProvider.getUriForFile(
        applicationContext,
        FILE_PROVIDER_AUTHORITY,
        file
    )
}