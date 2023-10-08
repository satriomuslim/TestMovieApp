package com.example.testmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmovieapp.databinding.ActivityMainBinding
import com.example.testmovieapp.ui.MovieAdapter
import com.example.testmovieapp.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private val movieViewModel = MovieViewModel()
    private lateinit var movieAdapter: MovieAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter(emptyList())

        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        movieViewModel.movieList.observe(this) { movies ->
            movieAdapter = MovieAdapter(movies)
            binding.movieRecyclerView.adapter = movieAdapter
        }
    }

}