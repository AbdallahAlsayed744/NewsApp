package com.example.newsapp.composobles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.onbordingdata
import com.example.newsapp.viewmodels.on_obordingevent

@Composable
fun onbording3(onbordingdata: onbordingdata, modifier: Modifier = Modifier,onclickback: () -> Unit,event:(on_obordingevent)->Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = onbordingdata.img), contentDescription ="onbording_Img", modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f), contentScale = ContentScale.Crop)
        Text(text = onbordingdata.title, modifier = Modifier.padding(top = 15.dp , start = 25.dp, end = 25.dp),style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold ,color = colorResource(
            id = R.color.text_title
        )
        )
        Text(text = onbordingdata.desc, modifier = Modifier.padding(horizontal = 25.dp), style = MaterialTheme.typography.titleMedium,color = colorResource(
            id = R.color.text_medium
        )
        )

        Row (horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 10.dp)){
            circularandicator(background1 = Color.LightGray, background2 = Color.LightGray, background3 = Color.Blue)

            Row {

                mybutton(text = "Back", onckick = { onclickback() }, background = colorResource(id =R.color.white), color = colorResource(
                    id = R.color.text_medium))

                mybutton(text = "Get Started", onckick = { event(on_obordingevent.saveappentry) }, background = Color.Blue, color = Color.White)


            }


        }




    }


}