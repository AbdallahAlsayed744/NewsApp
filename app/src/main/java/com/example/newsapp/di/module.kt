package com.example.newsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Builder
import com.example.newsapp.api.myApi
import com.example.newsapp.localDB.mylocaldata
import com.example.newsapp.localDB.mytypeconverter
import com.example.newsapp.repositry.homerepo
import com.example.newsapp.save_entry.localmanager
import com.example.newsapp.save_entry.myappsaveorread
import com.example.newsapp.save_entry.readentry
import com.example.newsapp.save_entry.saveentry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.newsapp.save_entry.localmanger
import com.example.newsapp.viewmodels.homeviewmodel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object module {
    @Provides
    @Singleton
    fun providelocalmanger(app: Application): localmanger {
        return localmanager(app)
    }

    @Provides
    @Singleton
    fun providesaveorreaddata(localmanager: localmanger): myappsaveorread {
        return myappsaveorread(saveentry(localmanager), readentry(localmanager))
    }

    @Provides
    @Singleton
    fun provideretrofitbuilder(): myApi {
        return Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(myApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: myApi, mylocaldata: mylocaldata): homerepo {
        return homerepo(apiService, mylocaldata)
    }

    @Provides
    @Singleton
    fun provideNewsViewModel(repository: homerepo): homeviewmodel {
        return homeviewmodel(repository)
    }

    @Provides
    @Singleton
    fun provideroomdb(app: Application):mylocaldata{
        return Room.databaseBuilder(app,mylocaldata::class.java,"newsdb")
            .fallbackToDestructiveMigration()
        .build()

    }



}