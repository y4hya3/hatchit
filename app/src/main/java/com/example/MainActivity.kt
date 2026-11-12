package com.example

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ComposeColor(0xFF090B14))
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                DecisionAppWebView()
            }
        }
    }
}

class AndroidAppBridge(private val context: Context) {
    @JavascriptInterface
    fun setAppLauncherIcon(iconId: String) {
        val pm = context.packageManager
        val defaultComponent = ComponentName(context, "com.example.MainActivity")
        val enderComponent = ComponentName(context, "com.example.MainActivityEnder")

        try {
            if (iconId == "ender") {
                pm.setComponentEnabledSetting(
                    enderComponent,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )
                pm.setComponentEnabledSetting(
                    defaultComponent,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
            } else {
                pm.setComponentEnabledSetting(
                    defaultComponent,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )
                pm.setComponentEnabledSetting(
                    enderComponent,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JavascriptInterface
    fun triggerHapticFeedback(patternType: String) {
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vm?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val effect = when (patternType) {
                    "heavy" -> VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
                    "crack" -> VibrationEffect.createWaveform(longArrayOf(0, 30, 20, 45), -1)
                    "reward" -> VibrationEffect.createWaveform(longArrayOf(0, 30, 50, 70), -1)
                    else -> VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE)
                }
                vibrator?.vibrate(effect)
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(30)
            }
        } catch (_: Exception) {}
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DecisionAppWebView() {
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    BackHandler(enabled = true) {
        val wv = webViewRef
        if (wv != null) {
            wv.evaluateJavascript("window.__eggDecideHandleBack ? window.__eggDecideHandleBack() : false") { result ->
                if (result != "true" && wv.canGoBack()) {
                    wv.goBack()
                }
            }
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setBackgroundColor(Color.parseColor("#090B14"))
                setLayerType(View.LAYER_TYPE_HARDWARE, null)
                overScrollMode = View.OVER_SCROLL_NEVER
                isHorizontalScrollBarEnabled = false
                isVerticalScrollBarEnabled = false

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    databaseEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                    cacheMode = WebSettings.LOAD_DEFAULT
                    setSupportZoom(false)
                    builtInZoomControls = false
                    displayZoomControls = false
                    setGeolocationEnabled(false)
                    mediaPlaybackRequiresUserGesture = false
                }

                addJavascriptInterface(AndroidAppBridge(context), "AndroidBridge")

                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()

                loadUrl("file:///android_asset/index.html")
                webViewRef = this
            }
        },
        update = { webView ->
            webViewRef = webView
        }
    )
}
