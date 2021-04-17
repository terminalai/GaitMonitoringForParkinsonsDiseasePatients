package com.thepyprogrammer.androidLib.web

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient

class GitHubWebViewClient(val activity: Activity) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String) = run {
        if (Uri.parse(url).host == "www.github.com") {
            // This is GitHub, so do not override; let WebView load the page
            view.loadUrl(url)
            false
        } else {
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                activity.startActivity(this)
            }
            true
        }
    }

    override fun onPageFinished(view: WebView, url: String?) {
        //Calling a javascript function in html page
        view.evaluateJavascript("showAndroidToast('Opened ${view.title}')", null)
    }
}