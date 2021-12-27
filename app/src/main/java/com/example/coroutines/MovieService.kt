package com.example.coroutines

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@QueryMap queryMap:Map<String,@JvmSuppressWildcards Any>):PopularMoviesResponse
}