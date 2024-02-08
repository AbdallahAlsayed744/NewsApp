package com.example.newsapp.composobles

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun bottomnavigation(navController: NavController) {
    var select_tab by remember {
        mutableStateOf(0)
    }
    Scaffold(

        bottomBar = {
            NavigationBar(containerColor = colorResource(id =R.color.bottomnav ),) {

                NavigationBarItem(selected = select_tab == 0, onClick = { select_tab = 0 }, icon = {
                    if (select_tab == 0) {

                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",
                            tint = Color.Blue

                        )

                    }
                    else {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",
                            tint = Color.Gray


                        )
                    }

                }, alwaysShowLabel = false ,label = { Text(text = "Home", fontWeight = FontWeight.Bold , color =  Color.Blue )})
                NavigationBarItem(selected = select_tab == 1, onClick = { select_tab = 1 }, icon = {
                    if (select_tab == 1) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search",
                            tint = Color.Blue

                        )

                    }
                    else {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search",
                            tint = Color.Gray


                        )
                    }


                },  alwaysShowLabel = false,label = { Text(text = "Search", fontWeight = FontWeight.Bold ,color = Color.Blue )})
                NavigationBarItem(selected = select_tab == 2, onClick = { select_tab = 2 }, icon = {
                        if (select_tab == 2) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                                contentDescription = "bookmark",
                                tint = Color.Blue

                            )

                        }
                        else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                                contentDescription = "bookmark",
                                tint = Color.Gray


                            )
                        }


                },  alwaysShowLabel = false,label = { Text(text = "BookMarks", fontWeight = FontWeight.Bold,color =  Color.Blue ) })

            }

        }
    ) {
        when (select_tab) {
            0 -> {
               Home(onclick = {title,content->
                   navController.navigate("newscreen/$title/$content")})
            }

            1 -> {
                Search(onclick = {title,content->
                    navController.navigate("newscreen/$title/$content")
                })
            }

            2 -> {
                bookmarks(onclick = {
                        title,content->
                    navController.navigate("newscreen/$title/$content")
                })
            }
        }

    }



}


