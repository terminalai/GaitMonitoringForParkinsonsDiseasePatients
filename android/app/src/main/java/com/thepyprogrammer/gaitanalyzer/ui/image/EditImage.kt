package com.thepyprogrammer.gaitanalyzer.ui.image

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class EditImage(val context: Context, val activity: Activity) {
    // my button click function
    init {
        Dexter.withActivity(activity)
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        showImagePickerOptions()
                    } else {
                        // TODO - handle permission denied case
                        checkPermission(
                            Manifest.permission.CAMERA,
                            ImageUtil.CAMERA_PERMISSION_CODE
                        )
                        checkPermission(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            ImageUtil.READ_EXTERNAL_STORAGE_PERMISSION_CODE
                        )
                        checkPermission(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            ImageUtil.WRITE_EXTERNAL_STORAGE_PERMISSION_CODE
                        )
                        checkPermission(
                            Manifest.permission.INTERNET,
                            ImageUtil.INTERNET_PERMISSION_CODE
                        )
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    fun checkPermission(permission: String, requestCode: Int) {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                activity,
                permission
            )
            == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat
                .requestPermissions(
                    activity, arrayOf(permission),
                    requestCode
                )
        }
    }

    private fun showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(
            context,
            object : ImagePickerActivity.PickerOptionListener {
                override fun onTakeCameraSelected() {
                    launchCameraIntent()
                }

                override fun onChooseGallerySelected() {
                    launchGalleryIntent()
                }
            })
    }

    private fun launchCameraIntent() {
        Intent(context, ImagePickerActivity::class.java).apply {
            putExtra(
                ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
                ImagePickerActivity.REQUEST_IMAGE_CAPTURE
            )

            // setting aspect ratio
            putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
            putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
            putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)

            // setting maximum bitmap width and height
            putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true)
            putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000)
            putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000)
            activity.startActivityForResult(this, ImageUtil.REQUEST_IMAGE)
        }
    }

    private fun launchGalleryIntent() {
        Intent(context, ImagePickerActivity::class.java).apply {
            putExtra(
                ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
                ImagePickerActivity.REQUEST_GALLERY_IMAGE
            )

            // setting aspect ratio
            putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
            putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
            putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
            activity.startActivityForResult(this, ImageUtil.REQUEST_IMAGE)
        }
    }
}