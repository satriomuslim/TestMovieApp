package com.example.testmovieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmovieapp.R
import com.example.testmovieapp.databinding.ItemMovieBinding
import com.example.testmovieapp.data.remote.ResultsItem
import kotlin.math.min

class MovieAdapter(private val movies: List<ResultsItem>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.binding.movieTitleTextView.text = movie.title
        holder.binding.movieOverviewTextView.text = movie.overview

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.moviePosterImageView)
    }

    override fun getItemCount(): Int {
        return min(10, movies.size)
    }

    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}