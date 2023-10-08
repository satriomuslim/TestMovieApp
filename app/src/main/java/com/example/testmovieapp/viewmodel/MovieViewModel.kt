package com.example.testmovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmovieapp.api.ApiConfig
import com.example.testmovieapp.remote.MovieResponse
import com.example.testmovieapp.remote.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()
    private val apiKey = "f7b67d9afdb3c971d4419fa4cb667fbf"

    private val _movieList = MutableLiveData<List<ResultsItem>>()
    val movieList: LiveData<List<ResultsItem>> get() = _movieList

    init {
        fetchMovieData()
    }

    private fun fetchMovieData() {
        apiService.movieApp(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    val movies = movieResponse?.results

                    if (movies != null) {
                        _movieList.postValue(movies.filterNotNull())
                        Log.e("SMW", "Fetched ${movies.size} movies")
                    } else {
                        Log.e("SMW", "Movie list is null")
                    }
                } else {
                    Log.e("SMW", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("SMW", "Error fetching movies: ${t.message}", t)
            }
        })
    }
}