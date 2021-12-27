package com.example.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding
    private val movieAdapter by lazy {
        MovieAdapter(::openDetail)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieRepository = (application as MovieApplication).movieRepository
        movieViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MovieViewModel(movieRepository) as T
            }
        }).get(MovieViewModel::class.java)
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.movieList.layoutManager = layoutManager
        binding.movieList.adapter = movieAdapter
        binding.movieList.addOnScrollListener(object : PaginationScrollListener(layoutManager) {

            override fun isLoading(): Boolean = movieViewModel.isApiInProgress()

            override fun isLastPage(): Boolean = movieViewModel.isLastPage()

            override fun loadMoreItems() {
                movieViewModel.fetchMovies()
            }
        })

        movieViewModel.popularList.observe(this, Observer {list ->
            val data = list.filter{
                it.release_date.startsWith(
                    Calendar.getInstance().get(Calendar.YEAR).toString()
                )
            }.sortedBy { it.title }
            Log.d("TAG","$data")
            movieAdapter.notify(data as MutableList<Movie>)
        })

        movieViewModel.error.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })

    }

    fun openDetail(movie:Movie){
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("DATA",movie)
        startActivity(intent)
    }

}