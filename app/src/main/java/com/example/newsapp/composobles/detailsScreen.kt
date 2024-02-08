package com.example.newsapp.composobles

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.model.Article
import com.example.newsapp.model.Source
import com.example.newsapp.viewmodels.homeviewmodel

@Composable
fun detailsscreen(navController: NavController,title:String?,content:String?){

        detailsbody(navController,title,content)

}

@Composable
fun detailsbody( navController: NavController, title:String?, content:String?,model:homeviewmodel= hiltViewModel()){
    val context= LocalContext.current
    val sh: SharedPreferences = LocalContext.current.getSharedPreferences("news", MODE_PRIVATE)
    val url=sh.getString("url","Ukj")
    val myimg=sh.getString("img","img")
    val myid=sh.getString("sourceid","wdwdec")
    val myname=sh.getString("sourcename","efef")

    var article:Article?=null
    model.getheadlinesoneforbookmark(Source(myid!!,myname!!))
    model.headlinesdataone.observe(LocalLifecycleOwner.current){
        data->
        article=data



   }




    Column(modifier = Modifier
        .fillMaxSize()

    ){

        Box(modifier = Modifier.height(120.dp)){
            mytopbar(
                onclickback = {navController.navigateUp()},
                onclickbook = {
                    article?.let {
                        model.insert(it)
                        Log.d("mybook",it.toString())
                    }
                    Toast.makeText(context, "Saved Successfully", Toast.LENGTH_LONG).show()
                              },
                onclickShare = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT,url)
                        it.type= "text/plain"
                        if (it.resolveActivity(context.packageManager)!=null){
                            context.startActivity(it)
                        }

                    }
                },

                onclickworled = {
                    Intent(Intent.ACTION_VIEW).also {
                    if (url!=null){
                        it.data= Uri.parse(url)
                    }
                    if (it.resolveActivity(context.packageManager)!=null){
                        context.startActivity(it)
                    }

                }
        }

            )

        }
        if (myimg!=null){
            SubcomposeAsyncImage(model = ImageRequest.Builder(context).data(myimg).build(), contentScale = ContentScale.Crop,contentDescription = "", modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .height(150.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(10.dp)
                )
            )
        }

        if (content != null) {
           Text(text = content ?:"no content available", modifier = Modifier.padding(top = 4.dp,start = 20.dp, end = 20.dp) ,style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = colorResource(
               id = R.color.text_title
           ))
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (title != null) {
            Text(text = title ?:"no title available", modifier = Modifier.padding(start = 20.dp, end = 20.dp),style = MaterialTheme.typography.displaySmall, fontSize = 26.sp ,fontWeight = FontWeight.Normal,color = colorResource(
                id = R.color.text_medium
            ))
        }
//      AsyncImage(model = img, contentDescription = "")
    }


}