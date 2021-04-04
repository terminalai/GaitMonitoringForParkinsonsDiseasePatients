package org.beeware.android

import android.content.Intent

interface IPythonApp {
    fun onCreate()
    fun onResume()
    fun onStart()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}