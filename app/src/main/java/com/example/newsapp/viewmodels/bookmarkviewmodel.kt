package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Article
import com.example.newsapp.repositry.bookmarkrepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
@HiltViewModel
class bookmarkviewmodel @Inject constructor(
    private val bookmarkrepo: bookmarkrepo
):ViewModel() {
    init {
        showmydata()
    }

    fun showmydata():LiveData<List<Article>>{
        return bookmarkrepo.showalldata
        Log.d("book",bookmarkrepo.showalldata.toString())


    }


}