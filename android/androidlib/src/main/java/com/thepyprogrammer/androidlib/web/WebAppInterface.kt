package com.thepyprogrammer.androidlib.web

import android.content.Context
import android.os.Build
import android.webkit.JavascriptInterface
import android.widget.Toast

/** Instantiate the interface and set the context  */
class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun getAndroidVersion(): Int {
        return Build.VERSION.SDK_INT
    }

    @JavascriptInterface
    fun showAndroidVersion(versionName: String?) {
        Toast.makeText(mContext, versionName, Toast.LENGTH_SHORT).show()
    }

}