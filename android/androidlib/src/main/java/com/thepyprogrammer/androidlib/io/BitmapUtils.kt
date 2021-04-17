package com.thepyprogrammer.androidlib.io

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface


fun rotateImageToCorrectOrientation(bitmap: Bitmap, filePath: String): Bitmap {
    val ei = android.media.ExifInterface(filePath)

    // Detects orientation of phone
    val orientation = ei.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )

    // Decides rotation of image based on phone orientation
    return when (orientation) {
        android.media.ExifInterface.ORIENTATION_ROTATE_90 -> bitmap.rotateImage(90f)

        ExifInterface.ORIENTATION_ROTATE_180 -> bitmap.rotateImage(180f)

        ExifInterface.ORIENTATION_ROTATE_270 -> bitmap.rotateImage(270f)

        else -> bitmap
    }
}

// Example of an extension function
// you can learn more about them here (https://kotlinlang.org/docs/reference/extensions.html)
fun Bitmap.rotateImage(angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
}