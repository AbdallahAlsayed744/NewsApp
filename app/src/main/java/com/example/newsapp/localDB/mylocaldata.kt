package com.example.newsapp.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(mytypeconverter::class)

abstract class mylocaldata:RoomDatabase() {
    abstract fun connenttodao():Dao


}