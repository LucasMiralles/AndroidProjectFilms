package com.example.firstapplication

import retrofit2.http.GET
import retrofit2.http.Query

interface Tmdb {
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(@Query("api_key") apikey : String, @Query("language") language: String): Films

    @GET("trending/tv/week")
    suspend fun getTrendingSeries(@Query("api_key") apikey : String, @Query("language") language: String): Series
}