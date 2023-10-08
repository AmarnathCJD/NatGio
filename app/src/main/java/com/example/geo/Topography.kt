package com.example.geo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

val TOP_MAP_IMAGE =
    "https://en-gb.topographic-map.com/?_path=api.maps.getOverlay&southLatitude=-80&westLongitude=-142.38333333333333&northLatitude=83.44055555555556&eastLongitude=175.07916666666668&zoom=2&version=202306301006"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopoMap(nav: androidx.navigation.NavController) {
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
                            .data(TOP_MAP_IMAGE)
                            .crossfade(true)
                            .build(),
                        contentDescription = "l",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .clip(
                                MaterialTheme.shapes.small
                            )
                            .shadow(
                                elevation = 15.dp
                            )

                    )
                }

            }

                TitleBox(title = "Topography")
                TopoSummary()
        }
    }
}

data class TopoI(
    val Name: String,
    val Desc: String,
)


@Composable
fun TopoSummary() {
    val TopQ = listOf(
        TopoI(
            Name = "Plain",
            Desc = "A plain is a broad area of relatively flat land. Plains are one of the major landforms, or types of land, on Earth. They cover more than one-third of the world’s land area. Plains exist on every continent. In fact, much of the land surface of Earth is plains. Plains are generally lower in elevation than other landforms, such as mountains. They are often found along the coast, where they are called coastal plains. Plains can also be found inland, where they are called interior plains. Plains are sometimes called lowlands or lowland plains. The term lowlands is also used to refer to areas that are lower in elevation than nearby areas. For example, the Great Plains of the United States are lower in elevation than the Rocky Mountains to the west. The Great Plains are also lower in elevation than the Canadian Shield to the north and the Appalachian Mountains to the east.",
        ),
        TopoI(
            Name = "Mountain",
            Desc = "A mountain is a landform that rises high above its surroundings. Mountains generally have steep slopes and a relatively small summit area. Mountains are taller than hills, but the term has no standardized geological meaning. Very rarely, mountains can be found in the plains. For example, the Black Hills of South Dakota are mountains that rise up from the surrounding plains. The Black Hills are also an example of an isolated mountain range. An isolated mountain range is a group of mountains that are not part of a larger mountain system. The Black Hills are not part of the Rocky Mountains, which lie to the west. The Black Hills are also not part of the Appalachian Mountains, which lie to the east. The Black Hills are part of the Great Plains, which lie to the east and north. The Great Plains are a large area of flat land.",
        ),
        TopoI(
            Name = "Hill",
            Desc = "A hill is a landform that rises above the surrounding terrain. It often has a distinct summit, although in areas with scarp/dip topography a hill may refer to a particular section of flat terrain without a massive summit (e.g. Box Hill, Surrey).",
        ),
        TopoI(
            Name = "Valley",
            Desc = "A valley is a low area between hills or mountains typically with a river running through it. In geology, a valley or dale is a depression that is longer than it is wide. The terms U-shaped and V-shaped are descriptive terms of geography to characterize the form of valleys. Most valleys belong to one of these two main types or a mixture of them, (at least) with respect of the cross section of the slopes or hillsides.",
        ),
        TopoI(
            Name = "Plateau",
            Desc = "A plateau is a flat, elevated landform that rises sharply above the surrounding area on at least one side. Plateaus occur on every continent and take up a third of the Earths land. Plateaus are different from mountain areas, which are typically higher and more uneven. Plateaus are also different from plains, which are flat areas that have only small changes in elevation. Plateaus are often found near mountains and mountain ranges. They can be formed by processes such as continental drift and volcanic eruptions. Plateaus can also be formed when the land is uplifted by tectonic activity. Plateaus can be formed by lava flows. When lava flows out of a volcano, it can form a plateau. The lava spreads out over a wide area and cools. The lava can also form a plateau when it flows into a valley. The lava fills the valley and forms a flat surface.",
        ),
        TopoI(
            Name = "Mesa",
            Desc = "A mesa is a flat-topped mountain or hill. It is an area of land that is higher than the land around it. Mesas are found in many parts of the world. They are especially common in the southwestern United States. Mesas are also found in other parts of the world, including Africa, Asia, and South America. Mesas are formed by erosion. Erosion is the process by which the surface of the Earth is worn away by the action of water, glaciers, winds, and waves. Mesas are formed when the surrounding land is worn away by erosion. The top of the mesa is made of hard rock that is resistant to erosion. The sides of the mesa are made of softer rock that is more easily eroded. The top of the mesa is often covered with a layer of soil. The soil is formed by the weathering of the rock. Weathering is the process by which rocks are broken down into smaller pieces.",
        ),
        TopoI(
            Name = "Butte",
            Desc = "A butte is a flat-topped mountain or hill. It is an area of land that is higher than the land around it. Buttes are found in many parts of the world. They are especially common in the southwestern United States. Buttes are also found in other parts of the world, including Africa, Asia, and South America. Buttes are formed by erosion. Erosion is the process by which the surface of the Earth is worn away by the action of water, glaciers, winds, and waves. Buttes are formed when the surrounding land is worn away by erosion. The top of the butte is made of hard rock that is resistant to erosion. The sides of the butte are made of softer rock that is more easily eroded. The top of the butte is often covered with a layer of soil. The soil is formed by the weathering of the rock. Weathering is the process by which rocks are broken down into smaller pieces.",
        ),
        TopoI(
            Name = "Canyon",
            Desc = "A canyon is a deep valley with steep sides. It is often carved from the Earth by a river. Canyons are found all over the world. They are especially common in the southwestern United States. Canyons are also found in other parts of the world, including Africa, Asia, and South America. Canyons are formed by erosion. Erosion is the process by which the surface of the Earth is worn away by the action of water, glaciers, winds, and waves. Canyons are formed when the surrounding land is worn away by erosion. The top of the canyon is made of hard rock that is resistant to erosion. The sides of the canyon are made of softer rock that is more easily eroded. The top of the canyon is often covered with a layer of soil. The soil is formed by the weathering of the rock. Weathering is the process by which rocks are broken down into smaller pieces.",
        ),
        TopoI(
            Name = "Cliff",
            Desc = "A cliff is a steep rock face. It is a vertical or nearly vertical rock exposure. Cliffs are found all over the world. They are especially common in the southwestern United States. Cliffs are also found in other parts of the world, including Africa, Asia, and South America. Cliffs are formed by erosion. Erosion is the process by which the surface of the Earth is worn away by the action of water, glaciers, winds, and waves. Cliffs are formed when the surrounding land is worn away by erosion. The top of the cliff is made of hard rock that is resistant to erosion. The sides of the cliff are made of softer rock that is more easily eroded. The top of the cliff is often covered with a layer of soil. The soil is formed by the weathering of the rock. Weathering is the process by which rocks are broken down into smaller pieces.",
        ),
        TopoI(
            Name = "Cave",
            Desc = "A cave is a natural underground space large enough for a human to enter. Caves are found all over the world. They are especially common in the southwestern United States. Caves are also found in other parts of the world, including Africa, Asia, and South America. Caves are formed by erosion. Erosion is the process by which the surface of the Earth is worn away by the action of water, glaciers, winds, and waves. Caves are formed when the surrounding land is worn away by erosion. The top of the cave is made of hard rock that is resistant to erosion. The sides of the cave are made of softer rock that is more easily eroded. The top of the cave is often covered with a layer of soil. The soil is formed by the weathering of the rock. Weathering is the process by which rocks are broken down into smaller pieces.",
        ),
        TopoI(
            Name = "Arch",
            Desc = "An arch is a curved structure that spans an opening. It is a curved structure that spans an opening. Arches are found all over the world. They are especially common in the southwestern United States. Arches are also found in other parts of the world, including Africa, Asia, and South America. Arches are formed by erosion. Erosion is the process by which the surface of the Earth is worn away by the action of water, glaciers, winds, and waves. Arches are formed when the surrounding land is worn away by erosion. The top of the arch is made of hard rock that is resistant to erosion. The sides of the arch are made of softer rock that is more easily eroded. The top of the arch is often covered with a layer of soil. The soil is formed by the weathering of the rock. Weathering is the process by which rocks are broken down into smaller pieces.",
        ),
    )
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 15.dp
            )
            .background(
                color = Color(0xFF0A0907)
            )

    ) {
        Column(
            modifier = Modifier
                .padding(

                )
                .background(
                    color = Color(0xFF081C4E)
                )
        ) {
            Divider(
                color = Color.DarkGray,
                thickness = 1.dp
            )
            TopQ.forEach {
                TopoItemQ(
                    it,
                )

                Divider(
                    color = Color.DarkGray,
                    thickness = 1.dp
                )
            }
        }
    }
}

@Composable
fun TopoItemQ(
    feat: TopoI
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
                        color = Color(0xFFCEA04D)
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
                            text = feat.Name,
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
                            text = "${truncateAtDotOr190(feat.Desc)} (Read More)",
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


fun truncateAtDotOr190(t: String) : String {
    // Find the index of last occurrence of . which is before 120
    val dotIndex = t.indexOf(".")
    val index = if (dotIndex > 190) 190 else dotIndex
    return t.substring(0, index)
}