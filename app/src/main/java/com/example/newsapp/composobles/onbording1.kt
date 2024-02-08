package com.example.newsapp.composobles

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.onbordingdata
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun onbording1(onbordingdata: onbordingdata,modifier: Modifier=Modifier,onclicknext:()->Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = onbordingdata.img), contentDescription ="onbording_Img", modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f), contentScale = ContentScale.Crop)
        Text(text = onbordingdata.title, modifier = Modifier.padding(top = 15.dp , start = 25.dp, end = 25.dp),style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold ,color = colorResource(
            id = R.color.text_title
        ))
        Text(text = onbordingdata.desc, modifier = Modifier.padding(horizontal = 25.dp),style = MaterialTheme.typography.titleMedium, color = colorResource(
            id = R.color.text_medium
        ))

        Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 10.dp)){
            circularandicator(background1 = Color.Blue, background2 = Color.LightGray, background3 = Color.LightGray)

           mybutton(text = "Next", onckick = { onclicknext() }, background = Color.Blue, color = Color.White)

        }



    }


}
//
//@Preview("Light Theme", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
//@Composable
//fun LightPreview() {
//    NewsAppTheme {
//        onbording1(onbordingdata = onbordingdata(R.drawable.onboarding3,"Lorem Ipsum is simply dummy","Lorem Ipsum is simply dummy text of the printing and typesetting industry"))
//
//
//    }
//
//}
//
