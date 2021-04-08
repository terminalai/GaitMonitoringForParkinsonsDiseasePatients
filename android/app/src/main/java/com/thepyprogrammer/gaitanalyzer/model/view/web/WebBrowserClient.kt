package com.thepyprogrammer.gaitanalyzer.model.view.web

import android.util.Log
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView


class WebBrowserClient : WebChromeClient() {
    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
        Log.d("JS", message)
        result.confirm()
        return true
    }
}
