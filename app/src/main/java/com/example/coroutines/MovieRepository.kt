package com.example.coroutines

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "5a7db3212c5abfd163d9857f3e3a245b"
    var pageNumber = 1
    private val widgetList = mutableListOf<Movie>()
    private val  movieLiveData = MutableLiveData<MutableList<Movie>>()
    private val  errorLiveData = MutableLiveData<String>()

    fun getMovies():LiveData<MutableList<Movie>>{
        return movieLiveData
    }

    fun getError():LiveData<String>{
        return errorLiveData
    }

    suspend fun fetchMovies(){
       try {
           val popularMovies = movieService.getPopularMovies(HashMap<String,Any>().apply {
               put("api_key",apiKey)
               put("page",pageNumber)
           })
           if(popularMovies.results.isNotEmpty()) {
               Log.d("HELLO","$pageNumber")
               pageNumber += 1
               widgetList.addAll(popularMovies.results)
               movieLiveData.postValue(widgetList)
           }
           else pageNumber = -1
       }catch (exception: Exception) {
           errorLiveData.postValue("An error occurred: ${exception.message}")

       }

    }



}