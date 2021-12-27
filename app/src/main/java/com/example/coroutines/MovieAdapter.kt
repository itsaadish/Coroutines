package com.example.coroutines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutines.databinding.MovieItemBinding

class MovieAdapter(private val openDetail: (Movie) -> Unit) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = mutableListOf<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context))
        val holder = MovieViewHolder(binding)
        binding.root.setOnClickListener {
            data[holder.adapterPosition].let {
                openDetail(it)
            }

        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MovieViewHolder -> {
                holder.bind(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun notify(list:MutableList<Movie>){
        data = list
        notifyDataSetChanged()

    }
}

class MovieViewHolder(private val binding: MovieItemBinding) :RecyclerView.ViewHolder(binding.root){
    private val imageUrl = "https://image.tmdb.org/t/p/w185/"
    fun bind(movie: Movie) {
        binding.movieTitle.text = movie.title
        Glide.with(binding.moviePoster.context).load("$imageUrl${movie.poster_path}").into(binding.moviePoster)
    }

}