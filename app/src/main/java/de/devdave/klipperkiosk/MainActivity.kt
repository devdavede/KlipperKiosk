@file:Suppress("DEPRECATION")

package de.devdave.klipperkiosk

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.devdave.klipperkiosk.ui.theme.KlipperKioskTheme


class MainActivity : ComponentActivity() {
    fun SetFullscreen(){
        (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                ).also { window.decorView.systemUiVisibility = it }
        actionBar?.hide()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun SetContent(){
        val klipperWebView = WebView(this)
        klipperWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val targetUrl = request?.url.toString()
                return Uri.parse(targetUrl).host == "http://klipper.local"
            }
        }

        klipperWebView.settings.setSupportMultipleWindows(false)
        klipperWebView.settings.builtInZoomControls = false
        klipperWebView.settings.displayZoomControls = false
        klipperWebView.settings.javaScriptEnabled = true
        klipperWebView.loadUrl("http://klipper.local")
        setContentView(klipperWebView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        SetFullscreen()

        setContent {
            KlipperKioskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetContent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KlipperKioskTheme {
    }
}