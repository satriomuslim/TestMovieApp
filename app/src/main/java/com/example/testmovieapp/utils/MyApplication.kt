package com.example.testmovieapp.utils

import android.app.Application
import androidx.room.Room
import com.example.testmovieapp.data.local.MovieDatabase

class MyApplication: Application() {
    companion object {
        lateinit var database: MovieDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, MovieDatabase::class.java, "movie_db")
            .build()
    }
}