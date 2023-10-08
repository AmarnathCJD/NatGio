package com.example.geo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.geo.ui.theme.fc

data class Menu(
    val name: String,
    val route: String,
    val icon: String,
    val desc: String

)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubMenu(nav: NavHostController) {
    val lazyListState = androidx.compose.foundation.lazy.LazyListState()
    val shuffledItems = listOf<Menu>(
        Menu(
            "VIIRS",
            "viirs",
            "viirs",
            "The Visible Infrared Imaging Radiometer Suite (VIIRS) Database"
        ),
        Menu(
            "Volcanic Data",
            "volcano",
            "volcano",
            "Volcanic data provided by the Smithsonian Institution's Global Volcanism Program"
        ),
        Menu(
            "Topography Map",
            "topoMap",
            "topoMap",
            "Soil Type Map map provided by the (NASA's Earth Observing System Data and Information System)"
        ),
        Menu(
            "Map Search",
            "mapSearch",
            "mapSearch",
            "Search for a location on the map, provided by GoogleEarth"
        ),
        Menu(
            "Weather",
            "weather",
            "weather",
            "Weather map provided by Meteoblue, a Swiss company that develops and delivers weather forecasts for any place on earth"
        ),
        Menu(
            "Earthquake Data",
            "startApp",
            "earthquake",
            "Earthquake data provided by the United States Geological Survey (USGS)"
        ),
    )
    Scaffold(
        bottomBar = { BottomBar(nav) },
        topBar = { TopBar(nav) },
    ) { it ->
        println(it)
        Column(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding()
                        .value.dp + 10.dp,
                    bottom = it.calculateBottomPadding()
                        .value.dp,
                )
                .background(
                    color = Color(0xFFFf8b740),
                )
        ) {
            LazyColumn(
                state = lazyListState,
            ) {
                itemsIndexed(shuffledItems) { _, item ->
                    SubMenuItem(item, nav)
                }
            }
        }
    }
}

@Composable
fun SubMenuItem(item: Menu, nav: NavHostController) {
    Row(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                nav.navigate(item.route)
            }

    ) {
        val painter = when (item.icon) {
            "volcano" -> painterResource(id = R.drawable.volcano)
            "topoMap" -> painterResource(id = R.drawable.topography)
            "mapSearch" -> painterResource(id = R.drawable.map)
            "weather" -> painterResource(id = R.drawable.weather)
            "earthquake" -> painterResource(id = R.drawable.seismic)
            "viirs" -> painterResource(id = R.drawable.icons8_satellite_64)
            else -> painterResource(id = R.drawable.plus)
        }

        Row(
            modifier = Modifier
                .padding(2.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                )
                .clip(
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                )
                .clickable {
                    nav.navigate(item.route)
                }
                .background(
                    color = Color(0xFFF0D6D6),
                )
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier

            ) {
                Image(
                    painter = painter,
                    contentDescription = "l",
                    contentScale = ContentScale.Crop,
                    modifier = androidx.compose.ui.Modifier
                        .size(75.dp)
                        .padding(10.dp)
                        .padding(

                        ),
                )
            }
            Column(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = item.name,
                    color = Color(0xFF313030),
                    fontSize = 20.sp,
                    fontFamily = fc
                )
                Text(
                    text = item.desc,
                    fontSize = 14.sp,
                    color = Color(0xFF313030)
                )
            }
        }
    }
}