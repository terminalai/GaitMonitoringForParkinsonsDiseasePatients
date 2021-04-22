package com.thepyprogrammer.gaitanalyzer.ui.image

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.yalantis.ucrop.UCrop
import java.io.File
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity


class ImagePickerActivity : AppCompatActivity() {
    private var lockAspectRatio = false
    private var setBitmapMaxWidthHeight = false
    private var ASPECT_RATIO_X = 16
    private var ASPECT_RATIO_Y = 9
    private var bitmapMaxWidth = 1000
    private var bitmapMaxHeight = 1000
    private var IMAGE_COMPRESSION = 80

    interface PickerOptionListener {
        fun onTakeCameraSelected()
        fun onChooseGallerySelected()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picker)
        val intent = intent
        if (intent == null) {
            Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_image_intent_null),
                    Toast.LENGTH_LONG
            ).show()
            return
        }
        ASPECT_RATIO_X = intent.getIntExtra(INTENT_ASPECT_RATIO_X, ASPECT_RATIO_X)
        ASPECT_RATIO_Y = intent.getIntExtra(INTENT_ASPECT_RATIO_Y, ASPECT_RATIO_Y)
        IMAGE_COMPRESSION = intent.getIntExtra(INTENT_IMAGE_COMPRESSION_QUALITY, IMAGE_COMPRESSION)
        lockAspectRatio = intent.getBooleanExtra(INTENT_LOCK_ASPECT_RATIO, false)
        setBitmapMaxWidthHeight = intent.getBooleanExtra(INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, false)
        bitmapMaxWidth = intent.getIntExtra(INTENT_BITMAP_MAX_WIDTH, bitmapMaxWidth)
        bitmapMaxHeight = intent.getIntExtra(INTENT_BITMAP_MAX_HEIGHT, bitmapMaxHeight)
        val requestCode = intent.getIntExtra(INTENT_IMAGE_PICKER_OPTION, -1)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            takeCameraImage()
        } else {
            chooseImageFromGallery()
        }
    }

    private fun takeCameraImage() = Dexter.withActivity(this)
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        fileName = System.currentTimeMillis().toString() + ".jpg"
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        takePictureIntent.putExtra(
                                MediaStore.EXTRA_OUTPUT,
                                getCacheImagePath(fileName)
                        )
                        if (takePictureIntent.resolveActivity(packageManager) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                        token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()

    private fun chooseImageFromGallery() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            val pickPhoto = Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                            startActivityForResult(pickPhoto, REQUEST_GALLERY_IMAGE)
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                            permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                            token: PermissionToken?
                    ) {
                        token?.continuePermissionRequest()
                    }
                }).check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> if (resultCode == RESULT_OK) {
                cropImage(getCacheImagePath(fileName))
            } else {
                setResultCancelled()
            }
            REQUEST_GALLERY_IMAGE -> if (resultCode == RESULT_OK) {
                val imageUri = data!!.data
                cropImage(imageUri)
            } else {
                setResultCancelled()
            }
            UCrop.REQUEST_CROP -> if (resultCode == RESULT_OK) {
                handleUCropResult(data)
            } else {
                setResultCancelled()
            }
            UCrop.RESULT_ERROR -> {
                val cropError: Throwable? = data?.let { UCrop.getError(it) }
                Log.e(TAG, "Crop error: $cropError")
                setResultCancelled()
            }
            else -> setResultCancelled()
        }
    }

    private fun cropImage(sourceUri: Uri?) {
        val destinationUri = Uri.fromFile(
                File(
                        cacheDir, queryName(
                        contentResolver, sourceUri
                )
                )
        )
        val options: UCrop.Options = UCrop.Options()
        options.setCompressionQuality(IMAGE_COMPRESSION)

        // applying UI theme
        //todo fix the color
        options.setToolbarColor(ContextCompat.getColor(this, R.color.white_10_percent))
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.white_10_percent))
        options.setActiveControlsWidgetColor(ContextCompat.getColor(this, R.color.white_10_percent))
        if (lockAspectRatio) options.withAspectRatio(
                ASPECT_RATIO_X.toFloat(),
                ASPECT_RATIO_Y.toFloat()
        )
        if (setBitmapMaxWidthHeight) options.withMaxResultSize(bitmapMaxWidth, bitmapMaxHeight)
        if (sourceUri != null) {
            UCrop.of(sourceUri, destinationUri)
                    .withOptions(options)
                    .start(this)
        }
    }

    private fun handleUCropResult(data: Intent?) {
        if (data == null) {
            setResultCancelled()
            return
        }
        val resultUri: Uri? = UCrop.getOutput(data)
        if (resultUri != null) {
            setResultOk(resultUri)
        }
    }

    private fun setResultOk(imagePath: Uri) {
        val intent = Intent()
        intent.putExtra("path", imagePath)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun setResultCancelled() {
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }

    private fun getCacheImagePath(fileName: String?): Uri {
        val path = File(externalCacheDir, "camera")
        if (!path.exists()) path.mkdirs()
        val image = File(path, fileName)
        return FileProvider.getUriForFile(this@ImagePickerActivity, "$packageName.provider", image)
    }

    companion object {
        private val TAG = ImagePickerActivity::class.java.simpleName
        const val INTENT_IMAGE_PICKER_OPTION = "image_picker_option"
        const val INTENT_ASPECT_RATIO_X = "aspect_ratio_x"
        const val INTENT_ASPECT_RATIO_Y = "aspect_ratio_Y"
        const val INTENT_LOCK_ASPECT_RATIO = "lock_aspect_ratio"
        const val INTENT_IMAGE_COMPRESSION_QUALITY = "compression_quality"
        const val INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT = "set_bitmap_max_width_height"
        const val INTENT_BITMAP_MAX_WIDTH = "max_width"
        const val INTENT_BITMAP_MAX_HEIGHT = "max_height"
        const val REQUEST_IMAGE_CAPTURE = 0
        const val REQUEST_GALLERY_IMAGE = 1
        var fileName: String? = null
        fun showImagePickerOptions(context: Context, listener: PickerOptionListener) {
            // setup the alert builder
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(context.getString(R.string.lbl_set_profile_photo))

            // add a list
            val animals = arrayOf(
                    context.getString(R.string.lbl_take_camera_picture),
                    context.getString(R.string.lbl_choose_from_gallery)
            )
            builder.setItems(animals) { _, which ->
                when (which) {
                    0 -> listener.onTakeCameraSelected()
                    1 -> listener.onChooseGallerySelected()
                }
            }

            // create and show the alert dialog
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        private fun queryName(resolver: ContentResolver, uri: Uri?): String {
            val returnCursor = resolver.query(uri!!, null, null, null, null)!!
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            val name = returnCursor.getString(nameIndex)
            returnCursor.close()
            return name
        }

    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                    val toast = Toast.makeText(
                            applicationContext,
                            "Moving back to Main Page",
                            Toast.LENGTH_LONG
                    )
                    toast.show()
                    navigateUpTo(Intent(this, MainActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}