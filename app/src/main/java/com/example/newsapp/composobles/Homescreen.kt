package com.example.newsapp.composobles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Homescreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 75.dp, start = 10.dp)) {
        bottomnavigation(navController)

    }
}