package com.example.newsapp.repositry

import android.util.Log
import com.example.newsapp.api.myApi
import com.example.newsapp.model.News
import retrofit2.Response
import javax.inject.Inject

class searchrepo @Inject constructor(
    private val api: myApi
) {

    suspend fun searchnews(category:String) :Response<News>{
        val response=api.getTopHeadlinestosearch(category,"us","12c6d0736f674a158056e2ff0fe02f06",1)
        if (response.isSuccessful&&response.body()!=null){
            Log.d("searchewsponse","successful")
            Log.d("searchewsponse",response.body().toString())
        }
        else{
            Log.d("searchewsponse",response.body().toString())
        }
        return response
    }



}