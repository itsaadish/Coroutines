package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.coroutines.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private val imageUrl = "https://image.tmdb.org/t/p/w185/"
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data: Movie? = intent.getParcelableExtra<Movie>("DATA")
           binding.releaseText.text = data?.release_date
           binding.overviewText.text = data?.overview
           binding.titleText.text = data?.title
        Glide.with(binding.moviePoster.context).load("$imageUrl${data?.poster_path}").fitCenter().into(binding.moviePoster)
    }
}