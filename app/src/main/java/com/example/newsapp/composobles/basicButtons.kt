package com.example.newsapp.composobles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun mybutton(text:String,onckick:()->Unit,background:Color,color: Color){
    Button(onClick = { onckick() }, colors = ButtonDefaults.buttonColors(containerColor = background, contentColor = color), shape = RoundedCornerShape(8.dp)
     ) {
        Text(text = text)

    }
}