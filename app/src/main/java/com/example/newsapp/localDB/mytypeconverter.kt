package com.example.newsapp.localDB

import androidx.room.TypeConverter
import com.example.newsapp.model.Source


class mytypeconverter {

    @TypeConverter
    fun fromsoursetostring(source: Source):String
    {
        return source.name
    }
    @TypeConverter
    fun fromstringtosource(source:String):Source
    {
        return Source(source,source)
    }
}