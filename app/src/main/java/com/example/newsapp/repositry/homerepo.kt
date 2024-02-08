package com.example.newsapp.repositry

import android.util.Log
import com.example.newsapp.api.myApi
import com.example.newsapp.localDB.mylocaldata
import com.example.newsapp.model.Article
import com.example.newsapp.model.News
import com.example.newsapp.model.Source
import retrofit2.Response
import javax.inject.Inject

class homerepo @Inject constructor(
    private val api:myApi,
    private val dao: mylocaldata
) {
    suspend fun gettoplinedews():Response<News>{
        val resposnse=api.getTopHeadlines("us","12c6d0736f674a158056e2ff0fe02f06",2)
        if (resposnse.isSuccessful&&resposnse.body()!=null){
            Log.d("response","Succesfull")
            Log.d("response",resposnse.body().toString())
        }
        else{
            Log.d("response","fail")

        }
        return resposnse


    }
    suspend fun gettoplinenewsforbookmarks(source: Source):Response<News>{
        val resposnse=api.getTopHeadlinesforbookmark("us","12c6d0736f674a158056e2ff0fe02f06",2,Source(source.id,source.name))
        if (resposnse.isSuccessful&&resposnse.body()!=null){
            Log.d("response","Succesfull")
            Log.d("response",resposnse.body().toString())
        }
        else{
            Log.d("response","fail")

        }
        return resposnse


    }


    suspend fun insert(article: Article){
        dao.connenttodao().insert(article)
    }




}