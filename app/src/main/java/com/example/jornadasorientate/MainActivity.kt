package com.example.jornadasorientate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        progress = findViewById(R.id.progress)

        setupWebView()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                loadJs()
                showWebView()
            }
        }
        webView.webViewClient = webViewClient
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://hiberus.com")
    }

    private fun loadJs() {
        webView.loadUrl(
            """javascript:(function f() {
                var header = document.getElementsByTagName("header")[0];
                var footer = document.getElementsByTagName("footer")[0];
                
                header.style.display = "none";
                footer.style.display = "none";
            })()"""
        )
    }

    private fun showWebView() {
        try {
            Thread.sleep(500)
            progress.visibility = View.GONE
            webView.visibility = View.VISIBLE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
