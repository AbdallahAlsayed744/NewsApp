package com.example.newsapp

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class onbordingdata (
   @DrawableRes val img  : Int,
    val title:String,
    val desc:String

)

val mydata:List<onbordingdata> = listOf(
    onbordingdata(
        img = R.drawable.onboarding1,
        title = "Lorem Ipsum is simply dummy",
        desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
    ),
        onbordingdata(
            img = R.drawable.onboarding2,
            title = "Lorem Ipsum is simply dummy",
            desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
        ),
        onbordingdata(
            img = R.drawable.onboarding3,
            title = "Lorem Ipsum is simply dummy",
            desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry"
        )
)
