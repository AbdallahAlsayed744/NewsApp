package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.repositry.searchrepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class searchviewmodel @Inject constructor(
    private val repo: searchrepo
) : ViewModel() {
    private val _headlinessortdata = MutableStateFlow<List<Article>>(emptyList())
    val headlinessportdata: StateFlow<List<Article>> get() = _headlinessortdata
    private val _isloading=MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isloading

    fun getsportheadlines(category:String){
        viewModelScope.launch(Dispatchers.IO) {
            _isloading.value=true
            try {
                val response=repo.searchnews(category)
                if (response.isSuccessful&&response.body()!=null){
                    _headlinessortdata.value=response.body()!!.articles
                }

            }
            catch (e:Exception){
                Log.d("networkerror",e.message.toString())

            }finally {
                _isloading.value=false
            }
        }

    }



}