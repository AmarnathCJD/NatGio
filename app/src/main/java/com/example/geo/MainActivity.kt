package com.example.geo

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.geo.ui.theme.GeoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoTheme {


                    var navController = rememberNavController()
                    NavHost(navController, startDestination = "homePage") {
                        composable(route = "startApp") {
                            SesmiMap(navController)
                        }
                        composable(route = "homePage") {
                            HomePage(navController)
                        }
                        composable(route = "volcano") {
                           Volcano(navController)
                        }
                        composable(route = "topoMap") {
                            TopoMap(navController)
                        }
                        composable(route = "mapSearch") {
                            SubArea(navController)
                        }
                        composable(route = "weather") {
                            Weather(navController)
                        }
                        composable(route = "viirs") {
                            //Viirs(navController)
                        }
                        composable(route = "subMenu") {
                            SubMenu(navController)
                        }

                    //SesmiMap()
                    //Volcano()
                    //TopoMap()
                    //SubArea(areaCode = "01")
                    //Weather(navController)
                    //SubMenu(navController)
                }
            }
        }



        window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }
}