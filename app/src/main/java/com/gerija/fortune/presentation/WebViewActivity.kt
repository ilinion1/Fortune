package com.gerija.fortune.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebSettings.LOAD_DEFAULT
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.gerija.fortune.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var binding:ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun startWebView() = with(binding){

        webViewId.loadUrl("http://html5test.com")
        webViewId.webViewClient = WebViewClient()

        webViewId.settings.javaScriptEnabled = true

        webViewId.clearCache(false)
        webViewId.settings.cacheMode = LOAD_DEFAULT

        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webViewId, true)

    }

    override fun onBackPressed() {
        if (binding.webViewId.canGoBack()) {
            binding.webViewId.goBack()
        } else super.onBackPressed()
    }
}