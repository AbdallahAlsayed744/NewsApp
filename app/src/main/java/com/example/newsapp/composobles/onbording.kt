package com.example.newsapp.composobles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.mydata
import com.example.newsapp.onbordingdata
import com.example.newsapp.utilis.Constants
import com.example.newsapp.viewmodels.on_obordingevent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onbordingscreen(event:(on_obordingevent)->Unit) {

    val pager= rememberPagerState {3}
    val cor= rememberCoroutineScope()
    HorizontalPager(state = pager) {
        when (it) {
            0 -> {
                onbording1(onbordingdata = mydata[0], onclicknext = {cor.launch { pager.animateScrollToPage(1) }} )
            }

            1 -> {
                onbording2(onbordingdata = mydata[1], onclickback = {cor.launch { pager.animateScrollToPage(0) }}, onclicknext = {cor.launch { pager.animateScrollToPage(2) }})
            }

            2 -> {

                onbording3(onbordingdata = mydata[2], onclickback = {cor.launch { pager.animateScrollToPage(1) }}, event = event)
            }

        }

    }

}