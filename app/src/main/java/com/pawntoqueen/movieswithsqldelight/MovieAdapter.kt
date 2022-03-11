package com.pawntoqueen.movieswithsqldelight

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pawntoqueen.movieswithsqldelight.databinding.MovieItemBinding
import com.pawntoqueen.sqldelight.models.db.Movies

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = arrayListOf<Movies>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): MovieViewHolder {
        val movieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int =movieList.size

    class MovieViewHolder(private val movieItemBinding : MovieItemBinding): RecyclerView.ViewHolder(movieItemBinding.root){
        fun bind(movie : Movies){
            movieItemBinding.movieName = movie.movie_name
            movieItemBinding.movieImdbPoint = movie.movie_imdb_point.toInt()
            movieItemBinding.releaseYear = movie.release_year.toInt()
            Log.d("ItemDatabase", "added")
        }
    }

    fun addmovielist(movies : List<Movies>){
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }


}