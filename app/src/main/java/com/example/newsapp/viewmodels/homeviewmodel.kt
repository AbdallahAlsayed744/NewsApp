package com.example.newsapp.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.model.Source
import com.example.newsapp.repositry.homerepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class homeviewmodel @Inject constructor(
    private val repo: homerepo,

) : ViewModel() {
    private val _headlinesdata = MutableStateFlow<List<Article>>(emptyList())
    val headlinesdata: StateFlow<List<Article>> get() = _headlinesdata
    private val _headlinesdataone = MutableLiveData<Article>()
    val headlinesdataone: LiveData<Article> get() = _headlinesdataone
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    init {
        getheadlines()

//        getheadlinesone()
    }

    fun getheadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value=true
            try {
                val response = repo.gettoplinedews()
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main){
                    _headlinesdata.value = response.body()!!.articles

                    }

                }


            } catch (e: Exception) {
                Log.d("errorNetwork", e.message.toString())

            }finally {
                _isLoading.value=false
            }

        }
    }
    fun getheadlinesone() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.gettoplinedews()
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main){
                        _headlinesdataone.value = response.body()!!.articles[0]
                    }

                }

            } catch (e: Exception) {
                Log.d("errorNetwork", e.message.toString())

            }
        }
    }
    fun getheadlinesoneforbookmark(source: Source) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.gettoplinenewsforbookmarks(source = Source(id = source.id, name = source.name))
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main){
                        _headlinesdataone.value = response.body()!!.articles[5]
                    }

                }

            } catch (e: Exception) {
                Log.d("errorNetwork", e.message.toString())

            }
        }
    }

    fun insert(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insert(article)
        }
    }


}