package com.example.newsapp.composobles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.model.Article
import com.example.newsapp.model.Source
import com.example.newsapp.utilis.Constants
import com.example.newsapp.viewmodels.onbording_viwmodel

@Composable
fun Mynaviation(startdistination:String){
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = startdistination ){
        composable(route=Constants.onbording){
            val model: onbording_viwmodel = hiltViewModel()
            onbordingscreen(event = model::myevent)
        }
        composable(route=Constants.sartscreen){
            Homescreen(navController)
        }
        composable(
            route = "newscreen/{discription}/{title}",
            arguments = listOf(
                navArgument("discription") {
                    type = NavType.StringType
                },
                navArgument("title") {
                    type = NavType.StringType
                },

            )
        ) {
            val content = it.arguments?.getString("discription")
            val title = it.arguments?.getString("title")
//            val Url=it.arguments?.getString("url")
            detailsscreen(navController,title = title, content = content)
        }
    }

}