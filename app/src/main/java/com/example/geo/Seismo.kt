package com.example.geo

import android.os.Build
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat

var IFRAME = "https://seismo.berkeley.edu/map/index.html"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SesmiMap(nav: androidx.navigation.NavController) {
    Scaffold(
        bottomBar = { BottomBar(nav) },
        topBar = { TopBar(nav) },
    ) { it ->
        val cfg = LocalConfiguration.current
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(
                    color = Color(0xFF000000)
                )
                .padding(
                    top = it.calculateTopPadding().value.dp,
                    bottom = it.calculateBottomPadding().value.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        cfg.screenHeightDp.dp / 2
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp),
                ) {
                    AndroidView(factory = {
                        WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            webViewClient = WebViewClient()
                            settings.javaScriptEnabled = true
                            loadUrl(IFRAME)
                        }
                    }, update = {
                        it.loadUrl(IFRAME)
                    })
                }
            }

            TitleBox(title = "Seismic Activity")
            SesmiSummary()
        }
    }
}

data class Sesimo(
    val features: List<FeatX>
)

data class FeatX(
    val properties: Feat
)

data class Feat(
    val mag: Float,
    val place: String,
    val time: Long,
    val updated: Long,
    val tz: Int,
    val title: String,
)

var SESMIC = mutableStateOf(
    Sesimo(
        features = listOf(
            FeatX(
                properties = Feat(
                    mag = 1.2f,
                    place = "San Francisco",
                    time = 1627777777,
                    updated = 1627777777,
                    tz = 0,
                    title = "San Francisco"
                )
            )
        )
    )
)


fun getSemsicFeed() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(
            "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.geojson"
        )
        .build()

    try {
        client.newCall(request)
            .enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("Error, ${e.message}")
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        if (body != null) {
                            val js = Gson().fromJson(body, Sesimo::class.java)
                            SESMIC.value = js
                        }
                    }
                }
            )
    } catch (e: Exception) {
        println("KUK $e")
    }
}

@Composable
fun SesmiSummary() {
    LaunchedEffect(Unit) {
        getSemsicFeed()
    }

    if (SESMIC.value.features.isNotEmpty() && SESMIC.value.features.size != 1) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = 15.dp
                )
                .background(
                    color = Color(0xFF070605)
                )

        ) {
            Column(
                modifier = Modifier
                    .padding(

                    )
                    .background(
                        color = Color(0xFF132046)
                    )
            ) {
                Divider(
                    color = Color.DarkGray,
                    thickness = 1.dp
                )
                SESMIC.value.features.forEach {
                    SesmiItem(
                        it.properties
                    )

                    Divider(
                        color = Color.DarkGray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@Composable
fun SesmiItem(
    feat: Feat
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                2.dp
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    2.dp
                )
                .clip(
                    MaterialTheme.shapes.medium
                )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        2.dp
                    )
                    .background(
                        color = Color(0xFF18130C)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )

            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            5.dp
                        )

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = feat.title,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    3.dp
                                )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Magnitude: ${feat.mag}",
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    3.dp
                                )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Occured: ${parseTimeToReadable(feat.time)}",
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    3.dp
                                )
                        )
                    }
                }
            }
        }
    }
}

fun parseTimeToReadable(t: Long): String {
    val date = java.util.Date(t)
    val formatter = SimpleDateFormat("HH:mm:ss, EEE MMM dd yyyy")
    return formatter.format(date)
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BottomBar(nav: androidx.navigation.NavController) {
    BottomAppBar(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
            .border(
                width = 30.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                horizontal = 0.dp,
                vertical = 0.dp
            ),
        containerColor = Color(0xFF161615),
        content = {
            Row(
                modifier =
                Modifier
                    .height(52.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 3.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier =
                    Modifier
                        .background(Color(0xFF161615))
                        .padding(
                            vertical = 1.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            imageVector = Icons.Filled.Home,
                            contentDescription = null,
                            modifier = Modifier
                                //
                                .padding(horizontal = 8.dp)
                                .padding(
                                    top = 1.dp,
                                )
                                .clickable {
                                    nav.navigate("homePage")
                                },
                            contentScale = ContentScale.Crop,
                            colorFilter =
                            ColorFilter.lighting(
                                add = Color(0xFFFFFFFF),
                                multiply = Color.White
                            )
                        )
                        Text(
                            text = "Home",
                            fontSize = 8.sp,
                            //fontFamily = sans_bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(
                                    bottom = 1.dp,
                                )
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(
                            vertical = 1.dp,
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier

                            .background(Color(0xFF161615)),
                    ) {
                        Image(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier
                                //
                                .padding(horizontal = 8.dp)
                                .padding(
                                    top = 1.dp,
                                )
                                .clickable {
                                    nav.navigate("mapSearch")
                                },
                            contentScale = ContentScale.Crop,
                            colorFilter =
                            ColorFilter.lighting(
                                add = Color(0xFFFFFFFF),
                                multiply = Color.White
                            )
                        )
                        Text(
                            text = "Search",
                            fontSize = 8.sp,
                            // fontFamily = sans_bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(
                                    bottom = 1.dp,
                                )
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(
                            vertical = 1.dp,
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(Color(0xFF161615)),
                    ) {
                        Image(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(
                                    top = 1.dp,
                                )
                                .clickable { nav.navigate("volcano") },
                            contentScale = ContentScale.Crop,
                            colorFilter =
                            ColorFilter.lighting(
                                add = Color(0xFFFFFFFF),
                                multiply = Color.White
                            )
                        )
                        Text(
                            text = "Volcano",
                            fontSize = 8.sp,
                            //fontFamily = sans_bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(
                                    bottom = 1.dp,
                                )
                        )
                    }

                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(
                            vertical = 1.dp,
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(Color(0xFF161615)),
                    ) {
                        Image(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(
                                    top = 1.dp,
                                )
                                .clickable { nav.navigate("startApp") },
                            contentScale = ContentScale.Crop,
                            colorFilter =
                            ColorFilter.lighting(
                                add = Color(0xFFFFFFFF),
                                multiply = Color.White
                            )
                        )
                        Text(
                            text = "Seismic",
                            fontSize = 8.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(
                                    bottom = 1.dp,
                                )
                        )
                    }

                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(nav: androidx.navigation.NavController) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .height(52.dp)
                        .padding(vertical = 15.dp)
                        .padding(
                            start = 8.dp
                        )
                        .clickable {
                            nav.navigate("homePage")
                        },
                    contentScale = ContentScale.Crop,
                    colorFilter =
                    ColorFilter.lighting(
                        add = Color(0xFFFFFFFF),
                        multiply = Color.White
                    )
                )
                Image(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .height(52.dp)
                        .padding(vertical = 15.dp)
                        .padding(
                            end = 24.dp
                        )
                        .clickable {
                            //navController.navigate("searchPanel")
                        },
                    contentScale = ContentScale.Crop,
                    colorFilter =
                    ColorFilter.lighting(
                        add = Color(0xFFFFFFFF),
                        multiply = Color.White
                    )
                )
            }
        },
        colors =
        TopAppBarDefaults.smallTopAppBarColors(
            titleContentColor = Color.Transparent,
            containerColor = Color.Transparent.copy(alpha = 0.1f),
        )
    )
}