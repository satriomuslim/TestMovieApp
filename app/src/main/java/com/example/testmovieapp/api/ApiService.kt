package com.example.testmovieapp.api

import com.example.testmovieapp.data.remote.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun movieApp(
        @Query("api_key") api_key: String,
    ) : Call<MovieResponse>

}