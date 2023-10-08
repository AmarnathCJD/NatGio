package com.example.geo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Volcano(nav: androidx.navigation.NavController) {
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
                    AsyncImage(
                        model =
                        ImageRequest.Builder(LocalContext.current)
                            .data("https://scitechdaily.com/images/Giant-Volcano-Eruption-Mountains-777x518.jpg?ezimgfmt=ngcb2/notWebP 777w")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(
                                cfg.screenWidthDp.dp
                            )
                            .height(
                                cfg.screenHeightDp.dp / 2
                            )
                            .shadow(
                                elevation = 15.dp
                            )
                    )
                }
            }
            TitleBox(title = "Volcanic Activity")

            VolcoSummary()
        }
    }
}

@Composable
fun TitleBox(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(0.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun VolcoSummary() {
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
                .padding(
                    40.dp
                )
                .background(
                    color = Color(0xFF070709)
                )

        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color(0xFF030203)
                    )
            ) {
                Divider(
                    color = Color.DarkGray,
                    thickness = 1.dp
                )
                Volcs.forEach {
                    VolciItem(
                        it
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
fun VolciItem(
    feat: Volcano
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
                        color = Color(0xFFBD8522)
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
                            text = feat.name,
                            fontWeight = FontWeight.Bold,
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
                            text = "Event Type: ${feat.type}",
                            fontWeight = FontWeight.Bold,
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
                            text = "Occured: ${feat.date} (${feat.vei})",
                            fontWeight = FontWeight.Bold,
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

data class Volcano(
    val name: String,
    val date: String,
    val type: String,
    val vei: Int,
)

val Volcs = listOf(
    Volcano(
        name = "Poas",
        date = "2023 Aug 2",
        type = "Confirmed Eruption",
        vei = 3
    ),
    Volcano(
        name = "Dempo",
        date = "2023 Jul 25",
        type = "Confirmed Eruption",
        vei = 0
    ),
    Volcano(
        name = "Ulawun",
        date = "2023 Jul 18",
        type = "Confirmed Eruption",
        vei = 1
    ),
    Volcano(
        name = "Shishaldin",
        date = "2023 Jul 12",
        type = "Confirmed Eruption",
        vei = 0
    ),
    Volcano(
        name = "Fragradalsfjall",
        date = "2023 Jul 10",
        type = "Confirmed Eruption",
        vei = 1
    ),
    Volcano(
        name = "San Cristobal",
        date = "2023 Jul 5",
        type = "Confirmed Eruption",
        vei = 0
    ),
    Volcano(
        name = "Fournaise",
        date = "2023 Jul 2",
        type = "Confirmed Eruption",
        vei = 0
    ),
    Volcano(
        name = "Kilauea",
        date = "2021-09-29",
        type = "Shield volcano",
        vei = 0
    ),
)