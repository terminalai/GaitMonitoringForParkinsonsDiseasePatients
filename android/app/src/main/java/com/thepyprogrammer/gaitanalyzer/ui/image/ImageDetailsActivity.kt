package com.thepyprogrammer.gaitanalyzer.ui.image

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.account.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.model.utils.string.SuperStringBuilder
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.util.*


class ImageDetailsActivity : AppCompatActivity() {
    private var REQUEST_IMAGE = 2169
    var imageView: ImageView? = null
    var imageInfoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setTitleTextColor(Color.WHITE)



        imageView = findViewById(R.id.imageDetailsImageView)
        imageInfoFile = File(filesDir, "profileImageURI.txt")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onStart() {
        super.onStart()
        loadImage()
    }


    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String?>,
            grantResults: IntArray
    ) {
        super
                .onRequestPermissionsResult(
                        requestCode,
                        permissions,
                        grantResults
                )
        // Checking whether user granted the permission or not.
        if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            Toast.makeText(
                    this,
                    "To select an icon, these permissions are required.",
                    Toast.LENGTH_SHORT
            )
                    .show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                val uri: Uri? = data?.getParcelableExtra("path")
                try {
                    if (uri != null) {
                        FirebaseUtil.uploadImage(uri)
                    }
                    // You can update this bitmap to your server
                    MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

                    //save uri to internal storage
                    writeData(uri.toString())

                    // loading profile image from local cache
                    loadImage()

                    //todo fix this one loadProfile(uri.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun writeData(s: String) {
        val output = PrintWriter(imageInfoFile)
        output.println(s)
        output.close()
        println(s)
    }

    private fun readData(): String {
        if (!imageInfoFile!!.exists()) {
            return ""
        }
        val scanner = Scanner(imageInfoFile)
        val string = SuperStringBuilder()

        while (scanner.hasNextLine())
            string.writeln(scanner.nextLine())

        scanner.close()
        return string.toString()
    }

    private fun loadImage() {
        val string: String = readData()
        if (string.isNotEmpty()) {
            imageView!!.setImageURI(Uri.parse(readData()))
        } else {
            imageView!!.setImageResource(R.drawable.face_trans)
        }

    }

}