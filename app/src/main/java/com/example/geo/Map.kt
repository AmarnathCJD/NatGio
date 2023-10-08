package com.example.geo

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URLEncoder

var GOOGLE_MAPS_API_KEY = "AIzaSyDsHVgl1tZrI_tlyI8Rg6n6SUEvSHQjfkE"

fun GetMap(lat: Float, long: Float, width: Int, height: Int): String {
    return "https://maps.googleapis.com/maps/api/staticmap?center=${lat},${long}&zoom=${CURRENT_ZOOM.value}&size=$width" +
            "x$height&maptype=satellite&markers=color:red%7Clabel:C%7C${lat},${long}&key=$GOOGLE_MAPS_API_KEY"
}

data class GeoCodeResponse(
    val features: List<Feature>
)

data class Feature(
    val geometry: Geometry
)

data class Geometry(
    val coordinates: List<Float>
)

fun GeoCode(addr: String, latMut: MutableState<Float>, longMut: MutableState<Float>) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(
            "https://api.geoapify.com/v1/geocode/search?text=${
                URLEncoder.encode(
                    addr,
                    "UTF-8"
                )
            }&apiKey=6c04f9e6099342468c38016d5995a418"
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
                            val js = Gson().fromJson(body, GeoCodeResponse::class.java)

                            val lat = js.features[0].geometry.coordinates[1]
                            val long = js.features[0].geometry.coordinates[0]

                            latMut.value = lat
                            longMut.value = long
                        }
                    }
                }
            )
    } catch (e: Exception) {
        println(e)
    }
}

var CURRENT_ZOOM = mutableStateOf(5)
var CURRENT_LOCATION = "Kerala"
var CURRENT_URL = ""
var LAT = mutableStateOf(0f)
var LONG = mutableStateOf(0f)

@Composable
fun Map() {
    val cfg = LocalConfiguration.current
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    cfg.screenHeightDp.dp / 2
                ),
        ) {
            LaunchedEffect(Unit) {
                GeoCode(
                    CURRENT_LOCATION, LAT, LONG
                )
            }

            if (LAT.value != 0f && LONG.value != 0f) {
                CURRENT_URL = GetMap(
                    LAT.value,
                    LONG.value,
                    cfg.screenWidthDp,
                    cfg.screenHeightDp / 2,
                )
            }

            Row(
                modifier =
                Modifier
                    .padding(
                        15.dp
                    )
                    .shadow(
                        elevation = 15.dp
                    )
                    .background(
                        Color.White
                    )
                    .border(
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                        width = 1.dp.times(0.4f),
                        color = Color.LightGray
                    )
            ) {
                AsyncImage(
                    model =
                    ImageRequest.Builder(LocalContext.current)
                        .data(
                            CURRENT_URL
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Title Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = CURRENT_URL.isEmpty()
                            )
                        )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = cfg.screenHeightDp.dp / 2 - 122.dp,
                        bottom = 2.dp,
                        start = cfg.screenWidthDp.dp - 75.dp,
                        end = 2.dp
                    )
                    .zIndex(2f)
            ) {
                Box(
                    modifier = Modifier.background(
                        Color.White.copy(alpha = 0.6f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(100.dp)
                            .border(
                                width = 1.dp.times(0.4f),
                                color = Color.LightGray
                            )
                            .padding(
                                start = 5.dp,
                                end = 5.dp,
                            ),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = androidx.compose.ui.res.painterResource(
                                id = R.drawable.plus
                            ),
                            contentDescription = "Zoom In",
                            alpha = 1f,
                            modifier = Modifier
                                .clickable {
                                    CURRENT_ZOOM.value += 1
                                    CURRENT_URL = GetMap(
                                        LAT.value,
                                        LONG.value,
                                        cfg.screenWidthDp,
                                        cfg.screenHeightDp / 2,
                                    )
                                }
                        )

                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp
                        )

                        Image(
                            painter = androidx.compose.ui.res.painterResource(
                                id = R.drawable.untitled_1
                            ),
                            contentDescription = "Zoom In",
                            alpha = 1f,
                            modifier = Modifier.clickable {
                                CURRENT_ZOOM.value -= 1
                                CURRENT_URL = GetMap(
                                    LAT.value,
                                    LONG.value,
                                    cfg.screenWidthDp,
                                    cfg.screenHeightDp / 2,
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox() {
    val cfg = LocalConfiguration.current
    val searchValue = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp)
            .shadow(
                elevation = 15.dp
            )
    ) {
        OutlinedTextField(
            value = searchValue.value,
            onValueChange = {
                searchValue.value = it
                if (searchValue.value.length % 2 == 0 && !searchValue.value.isNullOrEmpty()) {
//
                }
            },
            placeholder = { Text("Search for Places...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                containerColor = Color.DarkGray,
                placeholderColor = Color.Gray,
                focusedBorderColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                fontSize = 18.sp,
            ),
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Mm",
                    modifier = Modifier
                        .height(64.dp)
                        .padding(horizontal = 12.dp, vertical = 15.dp)
                        .clickable { },
                    contentScale = ContentScale.Crop,
                    colorFilter =
                    ColorFilter.lighting(
                        add = Color(0xFFFFFFFF),
                        multiply = Color.White
                    )
                )
            },

            trailingIcon = {
                Image(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Mm",
                    modifier = Modifier
                        .height(64.dp)
                        .padding(horizontal = 12.dp, vertical = 15.dp)
                        .clickable {
                            CURRENT_LOCATION = searchValue.value
                            GeoCode(
                                CURRENT_LOCATION, LAT, LONG
                            )
                            CURRENT_URL = GetMap(
                                LAT.value,
                                LONG.value,
                                cfg.screenWidthDp,
                                cfg.screenHeightDp / 2,
                            )
                        },
                    contentScale = ContentScale.Crop,
                    colorFilter =
                    ColorFilter.lighting(
                        add = Color(0xFFFFFFFF),
                        multiply = Color.White
                    )
                )
            },
        )
    }
}


@Composable
fun shimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}
