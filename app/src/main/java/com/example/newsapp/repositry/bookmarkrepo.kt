package com.example.newsapp.repositry

import androidx.lifecycle.LiveData
import com.example.newsapp.localDB.mylocaldata
import com.example.newsapp.model.Article
import javax.inject.Inject

class bookmarkrepo @Inject constructor(
    private val connectdao:mylocaldata
) {

    val showalldata= connectdao.connenttodao().alldata()


}