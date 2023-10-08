package com.example.geo

//import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.geo.ui.theme.fc


var SHOW_TOAST = mutableStateOf(true)

@Composable
fun HomePage(nav: androidx.navigation.NavController) {
    if (SHOW_TOAST.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(

                )
                /*  .clip(
                    RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp,
                        bottomEnd = 8.dp,
                        bottomStart = 8.dp
                    )
                )*/
                .background(Color.Black)
                .verticalScroll(
                    rememberScrollState()
                )

        ) {

            Row {
                Column(
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(8.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp
                            )
                        ),
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .background(Color(0xFFFF9800))
                            .clip(
                                RoundedCornerShape(
                                    topStart = 1.dp,
                                    topEnd = 1.dp,
                                    bottomEnd = 1.dp,
                                    bottomStart = 1.dp
                                )
                            )
                            .padding(
                                horizontal = 10.dp,
                                vertical = 8.dp
                            )
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "NatGio",
                            modifier = Modifier
                                .padding(bottom = 1.dp)
                                .background(Color.Transparent),
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 23.sp,
                            fontFamily = fc
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 8.dp
                        ),

                        )

            ) {
                GifImage()
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

            Spacer(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(13.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        nav.navigate("subMenu")
                    },
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            //start = 10.dp
                        )
                        .width(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp,
                        disabledElevation = 8.dp
                    )
                ) {

                    Text(
                        text = "Menu",
                        textAlign = TextAlign.Center
                    )

                }



                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 10.dp
                        )
                        .width(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp,
                        disabledElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "Updates",
                        textAlign = TextAlign.Center
                    )

                }
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 10.dp
                        )
                        .width(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF9800),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp,
                        disabledElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "NASA",
                        textAlign = TextAlign.Center
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp, bottom = 15.dp, end = 10.dp)
                    .fillMaxWidth()
                    .zIndex(2f)
                    .height(
                        300.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconButton(onClick = { nav.navigate("subMenu") }) {
                    Icon(
                        tint = Color(0xFFFF9800),
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Home Icon"
                    )
                }
            }

        }
    }
}

@Composable
fun GifImage() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()
    Image(

        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.earth)
                .apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 10.dp,
            ),
    )
}