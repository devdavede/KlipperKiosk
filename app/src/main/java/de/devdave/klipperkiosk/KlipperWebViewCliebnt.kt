package de.devdave.klipperkiosk

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class KlipperWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val targetUrl = request?.url.toString()
        return Uri.parse(targetUrl).host == "http://klipper.local"
    }
}