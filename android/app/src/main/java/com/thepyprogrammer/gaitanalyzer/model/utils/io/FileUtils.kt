package com.thepyprogrammer.gaitanalyzer.model.utils.io

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import java.io.*
import java.io.File
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
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

fun File.read() = String(Files.readAllBytes(Paths.get(absolutePath)))

fun File.readLines() = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8).toTypedArray()


fun Any.print(out: Writer) {
    when (out) {
        is PrintWriter -> out.println(toString())
        is BufferedWriter -> out.write(toString())
        is FileWriter -> out.write(toString())
    }
}

fun Any.print(outStream: OutputStream) {
    when (outStream) {
        is FileOutputStream -> outStream.write(toString().toByteArray())
        is DataOutputStream -> outStream.writeUTF(toString())
        is BufferedOutputStream -> DataOutputStream(outStream).writeUTF(toString())
        is PrintStream -> outStream.write(toString().toByteArray())
    }
}

fun Any.print(channel: FileChannel) {
    val strBytes = toString().toByteArray()
    val buffer: ByteBuffer = ByteBuffer.allocate(strBytes.size)
    buffer.put(strBytes)
    buffer.flip()
    channel.write(buffer)
}

fun Any.print(file: File) {
    val pw = PrintWriter(file)
    print(pw)
    pw.close()
}


fun Any.print(file: KFile) {
    if (file.out != null) file.out?.println(file) else print(
            File(
                    file.absolutePath
            )
    )
}

fun Any.print(parent: File, child: String) {
    print(File(parent, child))
}

fun Any.print(parent: String, child: String) {
    print(File(parent, child))
}

fun Any.print(uri: Uri) {
    print(KFile(uri, 'w'))
}

fun input(inp: String = ""): String {
    print(inp)
    val sc = Scanner(System.`in`)
    val line = sc.nextLine()
    sc.close()
    return line
}
