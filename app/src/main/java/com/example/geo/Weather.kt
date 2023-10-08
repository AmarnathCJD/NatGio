package com.example.geo

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Weather(nav: androidx.navigation.NavController) {
    Scaffold(
        bottomBar = { BottomBar(nav) },
        topBar = { TopBar(nav) },
    ) { it ->
        val cfg = LocalConfiguration.current
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(
                    color = Color(0xFF313030)
                )
                .padding(
                   // top = it.calculateTopPadding().value.dp-20.dp,
                    //b////ottom = it.calculateBottomPadding().value.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        cfg.screenHeightDp.dp
                    ),
            ) {
                Box(
                    modifier = Modifier
                ) {
                    AndroidView(factory = {
                        WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            webViewClient = WebViewClient()
                            settings.javaScriptEnabled = true
                            loadUrl("https://www.meteoblue.com/en/weather/maps/index#coords=0.47/0/74.1&map=windAnimation~rainbow~auto~10%20m%20above%20gnd~none")
                        }
                    }, update = {
                        it.loadUrl("https://www.meteoblue.com/en/weather/maps/index#coords=0.47/0/74.1&map=windAnimation~rainbow~auto~10%20m%20above%20gnd~none")
                    })
                }
            }

            TitleBox(title = "Weather Data")
            //WeatherSummary()
        }
    }
}

@Composable
fun WeatherSummary() {

}