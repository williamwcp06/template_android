package com.app.pages

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.common.base.BaseActivity
import com.app.databinding.WebviewActivityBinding

class WebViewActivity : BaseActivity() {
  private lateinit var binding: WebviewActivityBinding
  private lateinit var url: String

  companion object {
    fun launch(activity: Activity, url: String, title: String) {
      val intent = Intent(activity, WebViewActivity::class.java)
      intent.putExtra("url", url)
      intent.putExtra("title", title)
      activity.startActivity(intent)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = WebviewActivityBinding.inflate(layoutInflater)
    setContentView(binding.root)
    intent.extras?.getString("url", "")?.let {
      url = it
    }
    intent.extras?.getString("title", "WebView")?.let {
      title = it
    }
    loadWebView()

  }

  private fun loadWebView() {
    val webView: WebView = binding.webView
    webView.webViewClient = WebViewClient()
    webView.settings.setSupportZoom(true)
    webView.settings.javaScriptEnabled = true
    webView.loadUrl(url)
  }

  @SuppressLint("GestureBackNavigation")
  override fun onBackPressed() {
    super.onBackPressed()
    if (binding.webView.canGoBack()) {
      binding.webView.goBack()
    } else {
      finish()
    }
  }
}
