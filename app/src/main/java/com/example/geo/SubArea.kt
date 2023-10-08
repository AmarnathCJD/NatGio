package com.example.geo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubArea(nav: NavHostController) {
    val cfg = LocalConfiguration.current
    Scaffold(
        bottomBar = { BottomBar(nav) },
        topBar = { TopBar(nav) },
    ) { it ->
        Column(
            modifier = Modifier.background(
                color = Color(0xFF000000)
            ).padding(
                top = it.calculateTopPadding(),
                end = 0.dp,
            ).verticalScroll(rememberScrollState()),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 20.dp,
                        end = 3.dp,
                        start = 3.dp,
                        bottom = 0.dp,
                    )
                    .height(50.dp),
                elevation = CardDefaults.cardElevation(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    SearchBox()
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(

                    ),
                elevation = CardDefaults.cardElevation(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cfg.screenHeightDp.dp / (2 / 3))
                        .border(
                            width = 1.dp.times(0.1f),
                            color = Color.Transparent,
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Map()
                }
                val wBy2 = cfg.screenWidthDp.times(90f).div(100f) / 2
                val wBy3 = cfg.screenWidthDp.times(90f).div(100f) / 3

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        //.height(160.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Button(
                                onClick = { nav.navigate("volcanos") },
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(wBy2.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF89A5F0),
                                    contentColor = Color(0xFFFFFFFF),
                                ),
                            ) {
                                Text(
                                    text = "Volcanoes",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp, // Decrease the font size
                                    color = Color(0xFFFFFFFF),
                                )
                            }

                            Button(
                                onClick = { nav.navigate("startApp") },
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(wBy2.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFDA8585),
                                    contentColor = Color(0xFFFFFFFF),
                                )
                            ) {
                                Text(
                                    text = "Seismic",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp, // Decrease the font size
                                    color = Color(0xFFFFFFFF),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Button(
                                onClick = { nav.navigate("topoMap") },
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(wBy2.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFBE96D3),
                                    contentColor = Color(0xFFFFFFFF),
                                )
                            ) {
                                Text(
                                    text = "Topography",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp, // Decrease the font size
                                    color = Color(0xFFFFFFFF),
                                )
                            }

                            Button(
                                onClick = { nav.navigate("weather") },
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(wBy2.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFA08754),
                                    contentColor = Color(0xFFFFFFFF),
                                )
                            ) {
                                Text(
                                    text = "Weather",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp, // Decrease the font size
                                    color = Color(0xFFFFFFFF),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .height(35.dp)
                                    .width(wBy3.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF252523),
                                    contentColor = Color(0xFFFFFFFF),
                                ),
                            ) {
                                Text(
                                    text = getRandTemp(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp, // Decrease the font size
                                    color = Color(0xFFFFFFFF),
                                )
                            }

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .height(35.dp)
                                    .width(wBy3.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF402F49),
                                    contentColor = Color(0xFFFFFFFF),
                                ),
                            ) {
                                Text(
                                    text = "-/-",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp, // Decrease the font size
                                    color = Color(0xFFFFFFFF),
                                )
                            }

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .height(35.dp)
                                    .width(wBy3.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4B4B4A),
                                    contentColor = Color(0xFFFFFFFF),
                                ),
                            ) {
                                Text(
                                    text = "Sediments",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp, // Decrease the font size
                                    color = Color(0xFFFFFFFF),
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .padding(
                                    top = 15.dp,
                                    end = 13.dp,
                                    start = 13.dp,
                                    bottom = 13.dp,
                                ),
                        ) {
                            Text(
                                text = "The National Aeronautics and Space Administration (NASA /ˈnæsə/) is an independent agency of the U.S. federal government responsible for the civil space program, aeronautics research, and space research. Established in 1958, NASA succeeded the National Advisory Committee for Aeronautics (NACA) to give the U.S. space development effort a distinctly civilian orientation, emphasizing peaceful applications in space science.[4][5][6] NASA has since led most American space exploration, including Project Mercury, Project Gemini, the 1968–1972 Apollo Moon landing missions, the Skylab space station, and the Space Shuttle. NASA currently supports the International Space Station and oversees the development of the Orion spacecraft and the Space Launch System for the crewed lunar Artemis program, the Commercial Crew spacecraft, and the planned Lunar Gateway space station.",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp, // Decrease the font size
                                fontStyle = FontStyle.Normal,
                                color = Color(0xFFFFFFFF),
                            )
                        }

                        CircularProgressIndicator(
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp),
                            color = Color(0xFFFFFFFF),
                        )
                    }
                }
            }
        }
    }
}

fun getRandTemp(): String {
    return (32..53).random().toString() + " Celcius"
}