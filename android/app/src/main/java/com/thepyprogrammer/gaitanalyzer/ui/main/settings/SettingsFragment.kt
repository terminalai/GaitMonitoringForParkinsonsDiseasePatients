package com.thepyprogrammer.gaitanalyzer.ui.main.settings

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.image.EditImage
import com.thepyprogrammer.gaitanalyzer.ui.image.ImageUtil
import com.thepyprogrammer.gaitanalyzer.ui.main.MainActivity
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.util.*


class SettingsFragment : PreferenceFragmentCompat() {

    private var imageInfoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val shakePreference: SwitchPreferenceCompat? = findPreference("shake")
        val themePreference: SwitchPreferenceCompat? = findPreference("theme")

        shakePreference?.setOnPreferenceChangeListener { _: Preference, any: Any ->
            (activity as MainActivity).shakeToOpen = any as Boolean
            true
        }


        themePreference?.setOnPreferenceChangeListener { _: Preference, any: Any ->
            AppCompatDelegate.setDefaultNightMode(if (any as Boolean) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
            true
        }

        val imageButton: Preference? = findPreference("image")
        imageButton?.onPreferenceClickListener = Preference.OnPreferenceClickListener { //code for what you want it to do
            activity?.let { it1 -> EditImage(it1, it1) }
            true
        }

        val logout: Preference? = findPreference("logout")
        logout?.onPreferenceClickListener = Preference.OnPreferenceClickListener { //code for what you want it to do
            (activity as MainActivity).logout()
            true
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ImageUtil.REQUEST_IMAGE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val uri: Uri? = data?.getParcelableExtra("path")
                try {
                    if (uri != null) {
                        FirebaseUtil.uploadImage(uri)
                    }
                    // You can update this bitmap to your server
                    MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)

                    //save uri to internal storage
                    writeData(uri.toString())

                    //todo fix this one loadProfile(uri.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
    private fun writeData(s: String) {
        val imageInfoFile = File(activity?.filesDir, "profileImageURI.txt")
        val output = PrintWriter(imageInfoFile)
        output.println(s)
        output.close()
        println(s)
    }
}