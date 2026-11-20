package com.clipmaster.ai

import android.app.Activity
import android.os.Bundle
import android.webkit.*
import android.widget.*
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.content.Intent

class MainActivity : Activity() {
    private lateinit var web: WebView
    private var fileCallback: ValueCallback<Array<android.net.Uri>>? = null
    private val fileRequest = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(17, 24, 39))
        }
        val header = TextView(this).apply {
            text = "🎬  ClipMaster AI"
            textSize = 22f
            setTextColor(Color.WHITE)
            setPadding(20, 22, 16, 22)
        }
        layout.addView(header)
        web = WebView(this)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.allowFileAccess = false
        web.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                view: WebView?, callback: ValueCallback<Array<android.net.Uri>>?,
                params: FileChooserParams?
            ): Boolean {
                fileCallback?.onReceiveValue(null)
                fileCallback = callback
                val intent = params?.createIntent() ?: Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "video/*"
                }
                startActivityForResult(intent, fileRequest)
                return true
            }
        }
        web.webViewClient = WebViewClient()
        layout.addView(web, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
        ))
        setContentView(layout)
        if (BuildConfig.APP_URL.contains("YOUR-CLIPMASTER-SERVER")) {
            web.loadData(
                "<html><body style='font-family:sans-serif;padding:24px'>" +
                "<h2>ClipMaster AI is ready to connect</h2>" +
                "<p>Deploy the ClipMaster web backend, then set its HTTPS URL in " +
                "app/build.gradle.kts (APP_URL) and rebuild the APK.</p></body></html>",
                "text/html", "UTF-8"
            )
        } else {
            web.loadUrl(BuildConfig.APP_URL)
        }
    }

    @Deprecated("Deprecated in Android API, retained for broad device compatibility")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == fileRequest) {
            val result = if (resultCode == Activity.RESULT_OK && data?.data != null)
                arrayOf(data.data!!) else null
            fileCallback?.onReceiveValue(result)
            fileCallback = null
        }
    }

    override fun onBackPressed() {
        if (::web.isInitialized && web.canGoBack()) web.goBack() else super.onBackPressed()
    }
}
