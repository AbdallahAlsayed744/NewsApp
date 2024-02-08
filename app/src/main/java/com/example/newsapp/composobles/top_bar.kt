package com.example.newsapp.composobles

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mytopbar(

    onclickback: () -> Unit,
    onclickbook: () -> Unit,
    onclickShare: () -> Unit,
    onclickworled: () -> Unit,


){
    Scaffold (
        topBar = {
            TopAppBar(title = {},
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent,
                    actionIconContentColor = colorResource(id = R.color.body),
                    navigationIconContentColor = colorResource(id = R.color.body)
                ),
                navigationIcon = {
                    IconButton(onClick = { onclickback() }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack , contentDescription ="back" )

                    }
                },
                actions = {
                    IconButton(onClick = {onclickbook() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_bookmark_border_24), contentDescription = "worled")

                    }
                    IconButton(onClick = {onclickShare() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_share_24) , contentDescription = "share")
                    }
                    IconButton(onClick = {onclickworled() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_public_24), contentDescription = "public")

                    }



                }
            )
        }
    ){

    }
}

//@Composable
//@Preview(showBackground = true,showSystemUi = true)
//fun preview(){
//    topbar(onclickback = {  }, onclickbook = {  }, onclickShare = {  }, onclickworled = { } ) {
//
//    }
//}