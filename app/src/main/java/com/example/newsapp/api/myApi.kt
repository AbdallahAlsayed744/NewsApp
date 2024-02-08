package com.example.newsapp.api

import com.example.newsapp.model.News
import com.example.newsapp.model.Source
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface myApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") pagenum: Int,

    ): Response<News>

    @GET("top-headlines")
    suspend fun getTopHeadlinestosearch(
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") pagenum: Int,

        ): Response<News>

    @GET("top-headlines")
    suspend fun getTopHeadlinesforbookmark(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") pagenum: Int,
        @Query("source") source: Source

    ): Response<News>






}