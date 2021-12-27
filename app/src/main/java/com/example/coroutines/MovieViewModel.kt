package com.example.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Stream

class MovieViewModel(private val movieRepository: MovieRepository):ViewModel() {

    private var isApiInProgress = false
    init {
        fetchMovies()
    }

    val popularList:LiveData<MutableList<Movie>> = movieRepository.getMovies()
    val error:LiveData<String> = movieRepository.getError()

     fun fetchMovies(){
        isApiInProgress = true
     viewModelScope.launch(Dispatchers.IO) {
         movieRepository.fetchMovies()
     }
        isApiInProgress = false
 }

    fun isApiInProgress(): Boolean = isApiInProgress

    fun isLastPage() : Boolean = movieRepository.pageNumber == -1
}