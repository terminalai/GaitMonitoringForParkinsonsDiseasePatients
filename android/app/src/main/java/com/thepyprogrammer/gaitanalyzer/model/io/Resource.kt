package com.thepyprogrammer.gaitanalyzer.model.io

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

fun getUriFromRaw(context: Context, id: Int): Uri {
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(context.packageName)
        .path(id.toString())
        .build()
}